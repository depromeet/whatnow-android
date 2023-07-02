package com.depromeet.whatnow.ui.pictureView

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.depromeet.whatnow.component.WhatNowNaverMapIconButton
import com.depromeet.whatnow.component.WhatNowPictureUploadText
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.picture.PictureUploadText
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun PictureViewScreen(
    viewModel: PictureViewViewModel = hiltViewModel(),
    onBack: () -> Unit,
    imageUrl: String,
    iconUrl: String

) {
    val context = LocalContext.current
    val isRefresh by viewModel.isRefresh.collectAsState()
    val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.refresh()
        }
    }

    val configuration = LocalConfiguration.current
    val screeHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier.background(WhatNowTheme.colors.whatNowBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .height(screeHeight * 0.825f)
                .width(screenWidth),
        ) {


            Surface(
                modifier = Modifier
                    .height(screeHeight * 0.825f)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
            ) {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    model = imageUrl,
                    contentDescription = null
                )
            }



            WhatNowNaverMapIconButton(modifier = Modifier,
                iconButtonRes = R.drawable.arrow_back_ios,
                PaddingValues(start = 16.dp, top = 24.dp),
                alignment = Alignment.TopStart,
                color = Color(0xFFF9F9F9),
                tint = Color.Black,
                onClick = {
                    onBack()
                })

            AsyncImage(
                model = iconUrl,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 115.dp)
                    .size(180.dp)
            )

            WhatNowPictureUploadText(
                item = PictureUploadText(
                    "가는중",
                    R.drawable.on_the_way_icon,
                    true
                ), modifier = Modifier, onClick = {

                })

        }


        Row(
            modifier = Modifier
                .height(screeHeight * 0.175f)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {

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

                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "담장",
                    style = WhatNowTheme.typography.headline2.copy(
                        fontSize = 22.sp,
                        color = WhatNowTheme.colors.whatNowBlack,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}