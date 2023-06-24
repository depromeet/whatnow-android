package com.depromeet.whatnow.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.promiseActivate.PromiseEmojiTab
import com.depromeet.whatnow.ui.theme.WhatNowTheme


@Composable
fun WhatNowEmojiTab(
    modifier: Modifier = Modifier,
    selected: PromiseEmojiTab,
    onMusicEmojiClicked: () -> Unit,
    onPoopEmojiClicked: () -> Unit,
    onHeartEmojiClicked: () -> Unit,
    onFootprintEmojiClicked: () -> Unit,

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WhatNowEmojiTabItem(
            modifier = modifier,
            enabled = selected == PromiseEmojiTab.Music,
            emojiIconRes = R.drawable.emoji_music,
            count = 31,
            onClick = onMusicEmojiClicked
        )
        WhatNowEmojiTabItem(
            modifier = modifier,
            enabled = selected == PromiseEmojiTab.Poop,
            emojiIconRes = R.drawable.emoji_poop,
            count = 1,
            onClick = onPoopEmojiClicked
        )
        WhatNowEmojiTabItem(
            modifier = modifier,
            enabled = selected == PromiseEmojiTab.Heart,
            emojiIconRes = R.drawable.emoji_heart,
            count = 130,
            onClick = onHeartEmojiClicked
        )
        WhatNowEmojiTabItem(
            modifier = modifier,
            enabled = selected == PromiseEmojiTab.FootPrint,
            emojiIconRes = R.drawable.emoji_footprint,
            count = 0,
            onClick = onFootprintEmojiClicked
        )
    }
}

@Composable
fun WhatNowEmojiTabItem(
    modifier: Modifier,
    enabled: Boolean,
    @DrawableRes emojiIconRes: Int,
    count: Int,
    onClick: () -> Unit

) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(76.dp)
            .height(32.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp, color = if (enabled) WhatNowTheme.colors.gray50 else Color(0xff35373E)
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = if (enabled) modifier.background(WhatNowTheme.colors.whatNowPurple) else modifier.background(
                Color(0xff35373E)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.5.dp, end = 10.5.dp),
                horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = emojiIconRes),
                    contentDescription = null,
                    tint = Color.White,
                )

                Text(
                    modifier = modifier.width(27.dp),
                    text = count.toString(), style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp,
                        color = WhatNowTheme.colors.gray50,
                    ), textAlign = TextAlign.Center,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}