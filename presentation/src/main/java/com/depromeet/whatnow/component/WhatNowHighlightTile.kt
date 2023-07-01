package com.depromeet.whatnow.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Highlight
import com.depromeet.whatnow.ui.model.HighlightType
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White

@Composable
fun WhatNowHighlightTile(
    modifier: Modifier = Modifier,
    highlight: Highlight,
    isStart: Boolean = false,
    isEnd: Boolean = false,
    listSpacing: Dp = 16.dp
) {
    val stepperHeight =
        if (isStart) HIGHLIGHT_TILE_HEIGHT else HIGHLIGHT_TILE_HEIGHT + listSpacing

    Row(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        verticalAlignment = Alignment.Bottom
    ) {
        WhatNowStepper(height = stepperHeight, isStart = isStart, isEnd = isEnd)
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            modifier = Modifier
                .weight(1f)
                .height(HIGHLIGHT_TILE_HEIGHT),
            color = White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (highlight.type) {
                    HighlightType.Meet -> {
                        WhatNowImageStackVert(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            imageUrls = highlight.participants.map { it.profileImageUrl }
                        )
                    }
                    HighlightType.Arrive -> {
                        AsyncImage(
                            model = highlight.participants.first().profileImageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = highlight.text,
                        style = WhatNowTheme.typography.body2,
                        color = WhatNowTheme.colors.whatNowBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = WhatNowTheme.colors.gray700
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = highlight.location,
                            style = WhatNowTheme.typography.caption2,
                            color = WhatNowTheme.colors.gray700
                        )
                    }
                }
                Image(
                    painter = painterResource(
                        id = when (highlight.type) {
                            HighlightType.Meet -> R.drawable.img_highlight_meet
                            HighlightType.Arrive -> R.drawable.img_highlight_arrive
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }
}

private val HIGHLIGHT_TILE_HEIGHT = 80.dp
