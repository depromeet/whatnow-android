package com.depromeet.whatnow.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun WhatNowImageCarousel(
    modifier: Modifier = Modifier,
    imageUrls: List<String>
) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            pageSpacing = 8.dp
        ) {
            ElevatedCard(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(312 / 280f)
            ) {
                AsyncImage(
                    model = imageUrls[it],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(312 / 280f)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = imageUrls.size,
            indicatorWidth = 4.dp,
            indicatorHeight = 4.dp,
            indicatorShape = CircleShape,
            activeColor = WhatNowTheme.colors.whatNowPurple,
            inactiveColor = WhatNowTheme.colors.gray400,
            spacing = 4.dp
        )
    }
}
