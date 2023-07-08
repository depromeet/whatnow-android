package com.depromeet.whatnow.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.home.HomeViewModel
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


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowTimeOverMap(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    isLate: Boolean
) {
    val uiState by viewModel.uiState.collectAsState()

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

    val themeColor =
        if (isLate) Color(0xFFFF4747) else WhatNowTheme.colors.whatNowPurple

    Box() {

        Image(
            painter =
            if (isLate) painterResource(id = R.drawable.whatnow_home_late_icon)
            else painterResource(id = R.drawable.whatnow_home_wait_icon),
            contentDescription = null,
            modifier =
            if (isLate) modifier
                .padding(top = 3.dp, end = 26.dp)
                .align(Alignment.TopEnd)
            else modifier
                .padding(top = 11.dp, end = 18.dp)
                .align(Alignment.TopEnd)
        )

        Card(
            shape = RoundedCornerShape(
                topEnd = 28.dp, topStart = 28.dp, bottomEnd = 28.dp, bottomStart = 28.dp
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 41.dp, top = 64.dp)
                .background(WhatNowTheme.colors.gray50)
                .border(
                    BorderStroke(width = 1.dp, color = themeColor),
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

                        if (isLate) MarkerAndCircleOverlay(
                            OverlayImage.fromResource(R.drawable.time_over_late_marker),
                            timeOverLocations,
                            themeColor
                        )
                        else MarkerAndCircleOverlay(
                            OverlayImage.fromResource(R.drawable.map_marker),
                            timeOverLocations,
                            themeColor
                        )
                    }
                    Box(
                        modifier = modifier
                            .padding(bottom = 80.dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = stringResource(R.string.time_over),
                            style = WhatNowTheme.typography.headline1.copy(
                                fontSize = 24.sp, color = themeColor
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
                                text = "먹쨩이 되고싶은 모임", style = WhatNowTheme.typography.body1.copy(
                                    fontSize = 18.sp, color = WhatNowTheme.colors.whatNowBlack
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
                                        fontSize = 14.sp, color = WhatNowTheme.colors.gray700
                                    )
                                )
                            }

                        }
                        Surface(
                            modifier = Modifier
                                .width(84.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = themeColor.copy(alpha = 0.1f)
                            ),
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.background(themeColor)
                            ) {
                                if (isLate) InteractionTitle(stringResource(R.string.apologize))
                                else InteractionTitle(stringResource(R.string.urge))
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun InteractionTitle(title: String) {
    Text(
        text = title,
        style = WhatNowTheme.typography.body3.copy(
            fontSize = 14.sp,
            color = Color.White,
        ),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MarkerAndCircleOverlay(icon: OverlayImage, position: LatLng, color: Color) {
    Marker(
        icon = icon,
        state = MarkerState(position = position),
        anchor = Offset(0.5f, 0.5f)

    )

    CircleOverlay(
        center = position,
        color = color.copy(alpha = 0.2f),
        outlineColor = color.copy(alpha = 0.2f),
        radius = 3000.0
    )
}
