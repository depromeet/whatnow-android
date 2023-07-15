package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.domain.model.GetPromises
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.utils.toDate


@Composable
fun WhatNowNaverMapPromiseInfo(
    modifier: Modifier, promises: GetPromises, alignment: Alignment, padding: PaddingValues,
) {

    val addressList = promises.address.split(" ")
    val address = "%s %s".format(addressList[0], addressList[1])


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)

    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .align(alignment = alignment)
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x26B2B2B2),
                    ambientColor = Color(0x26B2B2B2)
                )
                .height(40.dp)
        ) {
            Row(
                modifier = modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(16.dp),
                    tint = WhatNowTheme.colors.whatNowBlack
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = address,
                    style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp, color = WhatNowTheme.colors.whatNowBlack
                    )
                )

                Icon(
                    painter = painterResource(id = R.drawable.watch),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(16.dp),
                    tint = WhatNowTheme.colors.whatNowBlack
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp, end = 8.dp),
                    text = promises.endTime.toDate(),
                    style = WhatNowTheme.typography.body3.copy(
                        fontSize = 14.sp, color = WhatNowTheme.colors.whatNowBlack
                    )
                )
            }
        }
    }
}