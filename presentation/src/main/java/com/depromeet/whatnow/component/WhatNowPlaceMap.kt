package com.depromeet.whatnow.component

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseAdd.PromiseAddViewModel
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowPlaceMap(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: PromiseAddViewModel,
) {

    val mapData by viewModel.locationMapData.collectAsState()

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
    val latitude = mapData.latitude
    val longitude = mapData.longitude
    val timeOverLocations = LatLng(latitude, longitude)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(timeOverLocations, 6.0)
    }

    Box {
        Card(
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                topStart = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 16.dp
            ),
            modifier = Modifier
                .background(WhatNowTheme.colors.gray50)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    NaverMap(
                        modifier = Modifier.height(240.dp),
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

                    Image(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.BottomEnd)
                            .width(164.dp)
                            .height(118.dp),
                        painter = painterResource(R.drawable.locationimg),
                        contentDescription = null
                    )

                }
            }
        }

    }
}