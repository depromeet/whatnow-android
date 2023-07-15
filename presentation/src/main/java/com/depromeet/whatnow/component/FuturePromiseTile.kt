package com.depromeet.whatnow.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.archive.ArchivePromiseTileAction
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.Roboto
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White

@Composable
fun FuturePromiseTile(
    promise: Promise,
    onShare: (Promise) -> Unit,
    onDelete: (Promise) -> Unit
) {
    var showDropdownMenu by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.month, promise.datetime.monthValue),
                style = WhatNowTheme.typography.body3.copy(fontFamily = Roboto),
                color = WhatNowTheme.colors.gray800
            )
            Text(
                text = promise.datetime.dayOfMonth.toString(),
                style = WhatNowTheme.typography.headline1.copy(fontFamily = Roboto),
                color = WhatNowTheme.colors.gray800
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            color = White,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = promise.title,
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
                            text = promise.location,
                            style = WhatNowTheme.typography.caption2,
                            color = WhatNowTheme.colors.gray700,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                WhatNowImageStack(imageUrls = promise.participants.map { it.profileImageUrl })
                Box(
                    modifier = Modifier.wrapContentSize()
                ) {
                    WhatNowIconButton(
                        iconRes = R.drawable.ic_more,
                        tint = WhatNowTheme.colors.whatNowBlack,
                        size = 24.dp,
                        onClick = { showDropdownMenu = showDropdownMenu.not() }
                    )
                    DropdownMenu(
                        expanded = showDropdownMenu,
                        onDismissRequest = { showDropdownMenu = false },
                        modifier = Modifier
                            .width(174.dp)
                            .background(color = White)
                            .clip(RoundedCornerShape(8.dp)),
                        offset = DpOffset(x = 14.dp, y = 14.dp)
                    ) {
                        ArchivePromiseTileAction.values().forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(id = it.textRes),
                                        style = WhatNowTheme.typography.body2,
                                        color = WhatNowTheme.colors.whatNowBlack
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = it.iconRes),
                                        contentDescription = null,
                                        tint = WhatNowTheme.colors.whatNowBlack,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                onClick = {
                                    when (it) {
                                        ArchivePromiseTileAction.Share -> onShare(promise)
                                        ArchivePromiseTileAction.Delete -> onDelete(promise)
                                    }
                                },
                                modifier = Modifier.background(color = White)
                            )
                        }
                    }
                }
            }
        }
    }
}
