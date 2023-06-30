package com.depromeet.whatnow.ui.picture

import android.Manifest
import android.app.Activity
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.depromeet.whatnow.component.WhatNowNaverMapIconButton
import com.depromeet.whatnow.component.WhatNowPictureUploadText
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun PictureScreen(
    viewModel: PictureViewModel = hiltViewModel(), onBack: () -> Unit,

    ) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }

    val permissions = if (Build.VERSION.SDK_INT <= 28) {
        listOf(
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    } else listOf(Manifest.permission.CAMERA)

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screeHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView

    val onUpload: (Uri?) -> Unit
// 이미지 선택 액티비티 launcher

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.uriToBitmap(imageUri = uri, context = context)
        }

    val bitmap by viewModel.bitmap.collectAsState()

    val pictureUploadText by viewModel.pictureUploadText.collectAsState()

    val pictureUploadImg by viewModel.pictureUploadImg.collectAsState()


    Column(
        modifier = Modifier.background(WhatNowTheme.colors.whatNowBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 권한이 부여되면 카메라 미리보기를 표시
        if (permissionState.allPermissionsGranted) {
            Box(
                modifier = Modifier
                    .height(screeHeight * 0.825f)
                    .width(screenWidth),
            ) {

                if (bitmap == null) {
                    Surface(
                        modifier = Modifier
                            .height(screeHeight * 0.825f)
                            .width(screenWidth),
                        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                    ) {
                        AndroidView(
                            factory = {
                                previewView = PreviewView(it)
                                viewModel.showCameraPreview(previewView, lifecycleOwner, context)
                                previewView
                            }, modifier = Modifier
                                .height(screeHeight * 0.825f)
                                .width(screenWidth)
                        )

                    }
                } else {
                    Surface(
                        modifier = Modifier
                            .height(screeHeight * 0.825f)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                    ) {
                        Image(
                            contentScale = ContentScale.Crop,
                            bitmap = bitmap!!.asImageBitmap(),
                            contentDescription = null
                        )
                    }
                }

                if (bitmap == null) {
                    WhatNowNaverMapIconButton(modifier = Modifier,
                        iconButtonRes = R.drawable.close,
                        PaddingValues(start = 16.dp, top = 24.dp),
                        alignment = Alignment.TopStart,
                        color = Color(0xFFF9F9F9),
                        tint = Color.Black,
                        onClick = {
                            onBack()
                        })

                    IconButton(modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 59.dp, bottom = 59.dp)
                        .size(24.dp),
                        onClick = { viewModel.onClickedFlash() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.flash_off),
                            contentDescription = null,
                        )
                    }

                    IconButton(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp)
                        .size(78.dp), onClick = {
                        if (permissionState.allPermissionsGranted) {
                            viewModel.captureAndSaveImage(context)

                        } else {
                            Toast.makeText(
                                context,
                                "Please accept permission in app settings",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pictrue_btton),
                            contentDescription = null,
                        )
                    }

                    IconButton(modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 59.dp, bottom = 59.dp)
                        .size(24.dp), onClick = {
                        viewModel.onClickedLensFacing(context)

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_sync),
                            contentDescription = null,
                        )
                    }
                } else {
                    WhatNowNaverMapIconButton(modifier = Modifier,
                        iconButtonRes = R.drawable.arrow_back_ios,
                        PaddingValues(start = 16.dp, top = 24.dp),
                        alignment = Alignment.TopStart,
                        color = Color(0xFFF9F9F9),
                        tint = Color.Black,
                        onClick = {
                            viewModel.bitmapInit()

                        })

                    Image(
                        painter = painterResource(id = pictureUploadImg),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 115.dp)
                            .size(180.dp)
                    )

                    LazyRow(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 44.dp),
                    ) {
                        itemsIndexed(pictureUploadText) { index, item ->
                            WhatNowPictureUploadText(item = item, modifier = Modifier, onClick = {
                                viewModel.onClickedPictureUploadText(index)
                                Log.d("ttt", pictureUploadText.toString())

                            })
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .height(screeHeight * 0.175f)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            if (bitmap == null) {

                Spacer(
                    modifier = Modifier.width(70.dp)
                )

                Surface(
                    onClick = { },
                    modifier = Modifier
                        .background(WhatNowTheme.colors.whatNowBlack)
                        .padding(start = 34.dp)

                ) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            modifier = Modifier
                                .width(70.dp)
                                .background(WhatNowTheme.colors.whatNowBlack)
                                .padding(bottom = 8.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.camera),
                            style = WhatNowTheme.typography.body1.copy(
                                fontSize = 18.sp, color = Color.White
                            )
                        )
                        Divider(
                            color = Color.White, modifier = Modifier
                                .width(70.dp)
                                .height(1.dp)
                        )
                    }
                }

                Surface(
                    onClick = {
                        galleryLauncher.launch("image/*")
                    },
                    modifier = Modifier
                        .background(WhatNowTheme.colors.whatNowBlack)
                        .padding(start = 34.dp)
                ) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(
                            modifier = Modifier
                                .width(70.dp)
                                .background(WhatNowTheme.colors.whatNowBlack)
                                .padding(bottom = 8.dp),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.gallery),
                            style = WhatNowTheme.typography.body1.copy(
                                fontSize = 18.sp, color = WhatNowTheme.colors.gray500
                            )
                        )

                        Divider(
                            color = WhatNowTheme.colors.whatNowBlack,
                            modifier = Modifier
                                .width(70.dp)
                                .height(1.dp)
                        )
                    }
                }
            } else {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(22.dp))
                        .size(64.dp)
                        .background(
                            color = Color(0xFFF9F9F9),
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = WhatNowTheme.colors.whatNowBlack
                    )
                }

            }
        }


    }
}