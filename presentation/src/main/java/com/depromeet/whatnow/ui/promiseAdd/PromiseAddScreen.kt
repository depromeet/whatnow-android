package com.depromeet.whatnow.ui.promiseAdd

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.util.*

@Composable
fun PromiseScreen(viewModel: PromiseAddViewModel) {
    val context = LocalContext.current
}

@Composable
fun Greeting(resId: Int, textSize: Int, textColor: Color) {
    Text(
        text = LocalContext.current.getString(resId),
        fontSize = textSize.sp,
        color = textColor
    )
}


@Composable
fun setCalendar() {
    Box(
        modifier = Modifier
            .padding(22.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val view =
                    LayoutInflater.from(context).inflate(R.layout.item_promise_calendar, null)
                val datePicker = view.findViewById<DatePicker>(R.id.datePicker1)

                // 현재 날짜로 DatePicker 초기화
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                datePicker.updateDate(year, month, day)

                view
            }
        )
    }
}

@Composable
fun setClock() {
    Box(
        modifier = Modifier
            .padding(22.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                LayoutInflater.from(context).inflate(R.layout.item_promise_clock, null)
            }
        )

    }
}

@Composable
fun setMakeBox(onClick: () -> Unit, titleResId: Int, MsgResId: Int, img: Int) {
    val expanded = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(120.dp)
            .padding(22.dp)
            .background(WhatNowTheme.colors.gray200)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Greeting(titleResId, 12, WhatNowTheme.colors.gray900)

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(
                        width = 0.5.dp,
                        color = WhatNowTheme.colors.whatNowBlack,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable(onClick = onClick)
                ) {
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 6.dp)
                    )
                    Greeting(MsgResId, 14, WhatNowTheme.colors.gray500)
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = false, widthDp = 320)
@Composable
fun DefaultPreview() {

    var isClockVisible by remember { mutableStateOf(false) }

    Surface(color = WhatNowTheme.colors.gray200) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 22.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.leftarrow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 6.dp)
                    )

                    Greeting(R.string.promise_title, 22, WhatNowTheme.colors.whatNowBlack)
                }


                setCalendar()

                if (!isClockVisible) {
                    setMakeBox(onClick = { isClockVisible = true },
                        R.string.promise_time,
                        R.string.promise_time_msg,
                        R.drawable.clock)
                } else {
                    setClock()
                }


//                setMakeBox(onClick = {}, "장소", "어디에사 만날까요?", R.drawable.map)
//                Spacer(modifier = Modifier.height(12.dp))
            }

            WhatNowPromiseAddBottomBar(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp))

        }
    }
}


@Composable
fun WhatNowPromiseAddBottomBar(
    modifier: Modifier = Modifier,
) {
    val bottomPanelHeight = 80.dp
    val bottomActionButtonSize = 72.dp
    val bottomBarItemIconSize = 20.dp

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(bottomPanelHeight)
                .fillMaxWidth(),
            color = WhatNowTheme.colors.gray900,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 37.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)

                ) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(74.dp)
                            .height(56.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = {

                                }
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = WhatNowTheme.colors.gray100
                        ),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.background(WhatNowTheme.colors.whatNowPurple)
                        ) {
                            Text(
                                text = LocalContext.current.getString(R.string.promise_bottom_button_next),
                                textAlign = TextAlign.Center,
                                color = Color(0xEEEEEFEE)
                            )
                        }
                    }
                }
            }
        }
    }
}