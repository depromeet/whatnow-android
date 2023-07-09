package com.depromeet.whatnow.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.home.HomeViewModel
import com.depromeet.whatnow.ui.promiseActivate.PromiseActivateActivity
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.CircleOverlay
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowActivityMap(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: HomeViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    val timeOver by viewModel.uiState.value.timeOver.collectAsState()

    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoom = 11.0, minZoom = 11.0)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isLogoClickEnabled = false,
                isZoomControlEnabled = false,
                isScrollGesturesEnabled = false,
                isRotateGesturesEnabled = false,
                isCompassEnabled = false,
                isScaleBarEnabled = false,
//                logoGravity = 1
            )
        )
    }
    val latitude =
        uiState.promisesUsersStatus.first().coordinateVo.latitude
    val longitude =
        uiState.promisesUsersStatus.first().coordinateVo.longitude
    val timeOverLocations = LatLng(latitude, longitude)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(timeOverLocations, 11.0)
    }

    Box() {
        Image(
            painter = painterResource(id = R.drawable.whatnow_home_ing_icon),
            contentDescription = null,
            modifier = modifier
                .padding(top = 4.dp, end = 16.dp)
                .align(Alignment.TopEnd)
        )
        Card(
            shape = RoundedCornerShape(
                topEnd = 28.dp,
                topStart = 28.dp,
                bottomEnd = 28.dp,
                bottomStart = 28.dp
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 41.dp, top = 64.dp)
                .background(WhatNowTheme.colors.gray50)
                .border(
                    BorderStroke(width = 1.dp, color = WhatNowTheme.colors.whatNowPurple),
                    shape = RoundedCornerShape(28.dp)
                )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .background(Color.White),
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    NaverMap(
                        cameraPositionState = cameraPositionState,
                        properties = mapProperties,
                        uiSettings = mapUiSettings,


                        ) {

                        Marker(
                            icon = OverlayImage.fromResource(R.drawable.map_marker),
                            state = MarkerState(position = timeOverLocations),
                            anchor = Offset(0.5f, 0.5f)

                        )

                        CircleOverlay(
                            center = timeOverLocations,
                            color = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.2f),
                            outlineColor = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.2f),
                            radius = 3000.0
                        )
                    }

                    Surface(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .align(Alignment.TopStart)
                            .width(110.dp)
                            .height(40.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.1f)
                        ),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(WhatNowTheme.colors.gray800)
                        ) {
                            Text(
                                text = stringResource(R.string.activated_promise),
                                style = WhatNowTheme.typography.body3.copy(
                                    fontSize = 14.sp,
                                    color = Color.White,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Box(
                        modifier = modifier
                            .padding(bottom = 80.dp)
                            .align(Alignment.Center)
                    ) {
                        WhatNowTimePickerPicker(
                            modifier = Modifier,
                            width = 43.dp,
                            height = 40.dp,
                            roundedCornerShape = RoundedCornerShape(16.dp),
                            hour = timeOver.first,
                            min = timeOver.second,
                            style = WhatNowTheme.typography.headline3.copy(
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        )
                    }
                }
                Column() {

                    Row(
                        modifier = Modifier
                            .background(WhatNowTheme.colors.gray100)
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column() {
                            Text(
                                text = uiState.promisesUsersStatus.first().title,
                                style = WhatNowTheme.typography.body1.copy(
                                    fontSize = 18.sp,
                                    color = WhatNowTheme.colors.whatNowBlack
                                )
                            )
                            Row(
                                modifier = modifier.padding(top = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.location),
                                    contentDescription = null,
                                )
                                Text(
                                    text = "서울시 종로구",
                                    modifier = modifier.padding(start = 4.dp),
                                    style = WhatNowTheme.typography.caption2.copy(
                                        fontSize = 14.sp,
                                        color = WhatNowTheme.colors.gray700
                                    )
                                )
                            }

                        }

                        Icon(
                            modifier = modifier.clickable {
                                PromiseActivateActivity.startActivity(
                                    context = context,
                                    uiState.promisesUsersStatus.first().promiseId
                                )
                            },
                            painter = painterResource(id = R.drawable.arrow_forward_ios_24),
                            contentDescription = null,
                            tint = WhatNowTheme.colors.whatNowBlack
                        )
                    }
                }
            }
        }

    }
}