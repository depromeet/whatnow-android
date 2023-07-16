package com.depromeet.whatnow.ui.setting

import android.graphics.Color
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.depromeet.whatnow.component.WhatNowButtonBar
import com.depromeet.whatnow.component.WhatNowButtonTopBar
import com.depromeet.whatnow.component.WhatNowProfileTextField
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.component.WhatNowSwitchBar
import com.depromeet.whatnow.component.WhatNowTextBar
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White


@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    navigateToWithdrawal: () -> Unit,
    navigateToSplash: () -> Unit,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let {
                val bitmap =
                    if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    }
                viewModel.onPickImage(bitmap)
            }
        }
    }

    val imageCropperOptions = CropImageOptions(
        fixAspectRatio = true,
        aspectRatioX = 1,
        aspectRatioY = 1,
        toolbarColor = Color.WHITE,
        toolbarBackButtonColor = Color.BLACK,
        toolbarTintColor = Color.BLACK,
        allowFlipping = false,
        allowRotation = false,
        cropMenuCropButtonTitle = context.getString(R.string.done),
        imageSourceIncludeCamera = false
    )

    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            val cropOptions = CropImageContractOptions(uri, imageCropperOptions)
            imageCropLauncher.launch(cropOptions)
        }

    Scaffold(
        topBar = {
            if (uiState.isEditMode) {
                WhatNowButtonTopBar(
                    onBack = { viewModel.setEditMode(false) },
                    titleRes = R.string.edit_profile,
                    buttonTextRes = R.string.done,
                    onClickButton = {
                        viewModel.updateProfile(context)
                        viewModel.refresh()
                        viewModel.setEditMode(false)
                    }
                )
            } else {
                WhatNowSimpleTopBar(
                    onBack = onBack,
                    titleRes = R.string.settings,
                )
            }
        }
    ) { innerPadding ->
        if (uiState.isEditMode) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.clickable { imagePickerLauncher.launch("image/*") }
                ) {
                    if (uiState.user.profileImageUrl.isBlank()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_default_profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(32.dp))
                        )
                    } else {
                        AsyncImage(
                            model = uiState.user.profileImageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(32.dp))
                        )
                    }
                    uiState.newProfileImage?.let {
                        Image(
                            it.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(32.dp))
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = 4.dp, y = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                WhatNowProfileTextField(
                    value = uiState.newNickname,
                    onValueChange = viewModel::inputNickname,
                    onClear = { viewModel.inputNickname("") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.edit_profile_help),
                    style = WhatNowTheme.typography.caption2,
                    color = WhatNowTheme.colors.gray600,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Box(
                    modifier = Modifier.clickable { viewModel.setEditMode(true) }
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                            .fillMaxWidth(),
                        color = WhatNowTheme.colors.whatNowBlack,
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box {
                                if (uiState.user.profileImageUrl.isBlank()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.img_default_profile),
                                        contentDescription = null,
                                        modifier = Modifier.size(120.dp)
                                    )
                                } else {
                                    AsyncImage(
                                        model = uiState.user.profileImageUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp)
                                            .clip(RoundedCornerShape(32.dp))
                                            .border(
                                                width = 1.dp,
                                                color = White,
                                                shape = RoundedCornerShape(32.dp)
                                            )
                                    )
                                }
                                Image(
                                    painter = painterResource(id = R.drawable.ic_profile_edit),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .offset(x = 4.dp, y = 4.dp)
                                        .align(Alignment.BottomEnd)
                                )
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Column {
                                Text(
                                    text = uiState.user.name,
                                    style = WhatNowTheme.typography.headline1
                                )
                                Text(
                                    text = stringResource(id = R.string.login_with_kakao),
                                    style = WhatNowTheme.typography.body2
                                )
                            }
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.img_settings_01),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 36.dp)
                            .align(Alignment.TopEnd)
                            .offset(y = (-12).dp)
                            .size(80.dp)
                    )
                }
                Divider(thickness = 8.dp, color = WhatNowTheme.colors.gray200)
                Column(
                    modifier = Modifier.padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WhatNowSwitchBar(
                        textRes = R.string.app_notification,
                        isSwitchedOn = uiState.isNotificationAvailable,
                        onSwitchOn = { viewModel.setNotification(true) },
                        onSwitchOff = { viewModel.setNotification(false) }
                    )
                    WhatNowTextBar(
                        textRes = R.string.version_info,
                        subtextRes = R.string.newest_version
                    )
                    WhatNowButtonBar(
                        textRes = R.string.usage_term,
                        onClick = {}
                    )
                    WhatNowButtonBar(
                        textRes = R.string.sign_out,
                        onClick = {
                            viewModel.logout()
                            navigateToSplash()
                        }
                    )
                    WhatNowButtonBar(
                        textRes = R.string.withdraw_whatnow,
                        onClick = { navigateToWithdrawal() }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_settings_02),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
    }
}
