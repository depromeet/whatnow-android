package com.depromeet.whatnow.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.CircleOverlay
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowNaverMap(modifier: Modifier) {
    var mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoom = 0.0, minZoom = 10.0)
        )
    }
    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = false)
        )
    }

    Box(Modifier.fillMaxSize()) {
        NaverMap(properties = mapProperties, uiSettings = mapUiSettings) {

            Marker(
                icon = OverlayImage.fromResource(R.drawable.map_marker),
                state = MarkerState(position = LatLng(37.532600, 127.024612)),
                anchor = Offset(0.5f, 0.5f)

            )

            CircleOverlay(
                center = LatLng(37.532600, 127.024612),
                color = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.2f),
                outlineColor = WhatNowTheme.colors.whatNowPurple.copy(alpha = 0.2f),

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhatNowNaverMapPreview() {
    WhatNowTheme {
        WhatNowNaverMap(Modifier)
    }
}
