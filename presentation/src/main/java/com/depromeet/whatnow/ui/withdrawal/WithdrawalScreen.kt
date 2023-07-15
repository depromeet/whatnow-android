package com.depromeet.whatnow.ui.withdrawal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.depromeet.whatnow.component.WhatNowButtonBar
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme

@Composable
fun WithdrawalScreen(
    viewModel: WithdrawViewModel,
    gotoTitle: () -> Unit,
    onBack: () -> Unit
) {

    var step by remember { mutableIntStateOf(1) }

    BackHandler {
        if (step == 1) onBack()
        else if (step < 3) step--
    }

    Scaffold(
        topBar = {
            if (step < 3) WhatNowSimpleTopBar(onBack = onBack, titleRes = R.string.withdrawal)
        }
    ) { innerPadding ->
        when (step) {
            1 -> {
                Column(
                    modifier = Modifier.padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.withdrawal_question),
                        style = WhatNowTheme.typography.headline1,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    WithdrawalOption.values().forEach {
                        WhatNowButtonBar(
                            textRes = it.textRes,
                            onClick = { step++ }
                        )
                    }
                }
            }

            2 -> {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 44.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.withdrawal_checklist_title),
                        style = WhatNowTheme.typography.headline1,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.withdrawal_checklist_subtitle),
                        style = WhatNowTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        WithdrawalChecklist.values().forEach {
                            Text(
                                text = stringResource(id = it.textRes),
                                style = WhatNowTheme.typography.body2,
                                color = WhatNowTheme.colors.gray700
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatNowTheme.colors.gray300,
                                contentColor = WhatNowTheme.colors.gray700
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.close),
                                style = WhatNowTheme.typography.body1
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                viewModel.withdraw()
                                step++
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatNowTheme.colors.whatNowError,
                                contentColor = WhatNowTheme.colors.gray50
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.withdraw),
                                style = WhatNowTheme.typography.body1
                            )
                        }
                    }
                }
            }

            3 -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 80.dp, bottom = 40.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.thanks_for_playing),
                        style = WhatNowTheme.typography.headline1,
                        modifier = Modifier.padding(horizontal = 36.dp)
                    )
                    Spacer(modifier = Modifier.height(54.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_withdrawal),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 36.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = gotoTitle,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatNowTheme.colors.whatNowBlack,
                            contentColor = WhatNowTheme.colors.gray50
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.back_to_title),
                            style = WhatNowTheme.typography.body1
                        )
                    }
                }
            }

            else -> {}
        }
    }
}