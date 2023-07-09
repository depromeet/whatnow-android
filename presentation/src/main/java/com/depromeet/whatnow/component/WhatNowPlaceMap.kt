package com.depromeet.whatnow.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun WhatNowPlaceMap(
    modifier: Modifier = Modifier,
    context: Context,
    viewModel: PromiseAddViewModel
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
    val timeOverLocations = LatLng(37.49797, 127.02763)
    Log.d("yw", "latitude = ${timeOverLocations.latitude}")
    Log.d("yw", "latitude = ${timeOverLocations.longitude}")
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
                    .height(194.dp)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    NaverMap(
                        modifier = Modifier.height(194.dp),
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