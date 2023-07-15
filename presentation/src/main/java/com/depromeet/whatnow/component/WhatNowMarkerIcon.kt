package com.depromeet.whatnow.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.depromeet.whatnow.domain.model.GetPromises
import com.depromeet.whatnow.domain.model.PromisesUsersStatus
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.overlay.OverlayImage


@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun WhatNowMarkerIcon(promisesUsersStatus: PromisesUsersStatus, promises: GetPromises) {

    promises.users.map {
        val overlayPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(it.profileImg)
                .size(Size.ORIGINAL) // Set the target size to load the image at.
                .build()
        )

        val overlayImageLoadedState = overlayPainter.state

        if (overlayImageLoadedState is AsyncImagePainter.State.Success) {

            val overlayImageBitmap = overlayImageLoadedState.result.drawable.toBitmap()

            /**
             * 사용자 마커
             * */
            Marker(
                icon = OverlayImage.fromBitmap(overlayImageBitmap),
                width = 56.dp,
                height = 56.dp,
                state = MarkerState(
                    position = LatLng(
                        promisesUsersStatus.userLocation.latitude,
                        promisesUsersStatus.userLocation.longitude
                    )
                ),
                anchor = Offset(0.5f, 0.5f),
            )

            Marker(
                icon = OverlayImage.fromResource(R.drawable.status_change_washing_img),
                width = 88.dp,
                height = 88.dp,
                state = MarkerState(
                    position = LatLng(
                        promisesUsersStatus.userLocation.latitude,
                        promisesUsersStatus.userLocation.longitude
                    )
                ),
                anchor = Offset(0.5f, 0.5f),
                captionText = it.nickname,
                captionTextSize = 14.sp,
                captionColor = WhatNowTheme.colors.whatNowBlack,
            )
        }
    }

}