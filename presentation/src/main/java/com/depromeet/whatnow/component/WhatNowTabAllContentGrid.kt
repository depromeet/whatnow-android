package com.depromeet.whatnow.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.domain.model.Users

@Composable
fun WhatNowTabAllContentGrid(
    users: Users,
    modifier: Modifier
) {

//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(bottom = 6.dp)
//    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WhatNowProfile(users = users, profileImageSize = 72.dp, statusImageSize = 104.dp)
            Text(text = users.nickname, modifier = modifier.padding())
        }

//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//
//        ) {
//            WhatNowProfile(users = users, profileImageSize = 72.dp, statusImageSize = 104.dp)
//            Text(text = users.nickname, modifier = modifier.padding())
//
//        }
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            WhatNowProfile(users = users, profileImageSize = 72.dp, statusImageSize = 104.dp)
//            Text(text = users.nickname, modifier = modifier.padding())
//
//        }
//
//
//    }

}
