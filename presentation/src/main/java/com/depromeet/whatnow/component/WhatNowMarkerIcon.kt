package com.depromeet.whatnow.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.overlay.OverlayImage


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowMarkerIcon(url: String, position: LatLng) {

    val overlayPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(url)
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    val overlayImageLoadedState = overlayPainter.state

    if (overlayImageLoadedState is AsyncImagePainter.State.Success) {

        SideEffect {
            println("🔥 COMPOSING...")
        }

        val overlayImageBitmap = overlayImageLoadedState.result.drawable.toBitmap()


        /**
         * 사용자 마커
         * */
        Marker(
            icon = OverlayImage.fromBitmap(overlayImageBitmap),
            width = 56.dp,
            height = 56.dp,
            state = MarkerState(position = position),
            anchor = Offset(0.5f, 0.5f),
            captionText = "침착맨"
        )
    }
}