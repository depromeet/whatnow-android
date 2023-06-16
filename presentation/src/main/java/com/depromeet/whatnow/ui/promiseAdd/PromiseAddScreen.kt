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
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.MaterialColors
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
            .padding(start = 22.dp, end = 22.dp)
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
fun setPlace(onClick: () -> Unit, titleResId: Int, MsgResId: Int, img: Int) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(120.dp)
            .padding(start = 22.dp, end = 22.dp, top = 8.dp, bottom = 8.dp)
            .background(
                color = WhatNowTheme.colors.whatNowBlack,
                shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = LocalContext.current.getString(titleResId),
                fontSize = 12.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .padding(start = 16.dp)
                        .clickable(onClick = onClick)
                ) {
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 11.dp)
                    )

                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = WhatNowTheme.colors.gray50, // 수정: 배경색을 지정해줍니다.
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            textColor = Color.Blue
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp // 원하는 크기로 설정
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(50.dp)
                            .width(238.dp)
                    )
                }
            }
        }
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
fun setMakeBox(
    onClick: () -> Unit,
    titleResId: Int,
    MsgResId: Int,
    img: Int,
    isComplete: Boolean,
) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp, top = 8.dp, bottom = 8.dp)
            .background(WhatNowTheme.colors.gray50)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (!isComplete) {
                IncompleteContent(titleResId)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .border(
                        width = if (isComplete) 0.dp else 0.5.dp,
                        color = if (isComplete) Color.White else WhatNowTheme.colors.whatNowBlack,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = if (isComplete) Color.White else WhatNowTheme.colors.gray50,
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

@Composable
fun IncompleteContent(titleResId: Int) {
    Greeting(titleResId, 12, WhatNowTheme.colors.gray900)
    Spacer(modifier = Modifier.height(6.dp))
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(device = Devices.PIXEL_2)
@Composable
fun DefaultPreview() {

    var isClockDataSet by remember { mutableStateOf(false) }
    var isPlaceDataSet by remember { mutableStateOf(false) }

    var isSetDateValue by remember { mutableStateOf(false) }
    var isClockVisible by remember { mutableStateOf(false) }
    var isPlaceVisible by remember { mutableStateOf(false) }

    Surface(color = WhatNowTheme.colors.gray50) {
        Column(modifier = Modifier.fillMaxWidth()) {
            WhatNowPromiseAddTopBar(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
                buttonOnClick = { /*TODO*/ })

            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)) {


                if (isSetDateValue) {
                    setMakeBox(
                        onClick = {
                            isSetDateValue = false
                            isClockVisible = false
                            isPlaceVisible = false
                        },
                        R.string.promise_calendar,
                        R.string.promise_calendar_msg,
                        R.drawable.calendar,
                        true)
                } else {
                    setCalendar()
                    isClockDataSet = false
                }

                if (!isClockDataSet && !isClockVisible) {
                    setMakeBox(
                        onClick = {
                            isSetDateValue = true
                            isClockVisible = true
                            isClockDataSet = true
                        },
                        R.string.promise_time,
                        R.string.promise_time_msg,
                        R.drawable.clock,
                        false)
                } else if (isClockDataSet && !isClockVisible) {
                    setMakeBox(
                        onClick = {
                            isSetDateValue = true
                            isClockVisible = true
                            isClockDataSet = true
                        },
                        R.string.promise_time,
                        R.string.promise_time_msg,
                        R.drawable.clock,
                        true)
                } else {
                    setClock()
                    isPlaceDataSet = false
                }

                if (!isPlaceDataSet && !isPlaceVisible) {
                    setMakeBox(
                        onClick = {
                            isSetDateValue = true
                            isClockVisible = false
                            isClockDataSet = true
                            isPlaceVisible = true
                        },
                        R.string.promise_place,
                        R.string.promise_place_msg,
                        R.drawable.map,
                        false)
                } else if (isPlaceDataSet && !isPlaceVisible) {
                    setMakeBox(
                        onClick = {
                            isSetDateValue = true
                            isClockVisible = false
                            isClockDataSet = true
                            isPlaceVisible = true
                        },
                        R.string.promise_place,
                        R.string.promise_place_msg,
                        R.drawable.map,
                        true)
                } else {
                    setPlace(onClick = {},
                        R.string.promise_place_select,
                        R.string.promise_place_msg,
                        R.drawable.whitemap)
                }

            }

            WhatNowPromiseAddBottomBar(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp), buttonOnClick = {
                if (!isSetDateValue) {
                    isSetDateValue = true
                    isClockVisible = true
                    isPlaceVisible = false
                } else if (isClockVisible) {
                    isSetDateValue = true
                    isClockVisible = false
                    isPlaceVisible = true
                } else if (isPlaceVisible) {
                    isSetDateValue = true
                    isClockVisible = false
                    isPlaceVisible = true
                }
            })
        }
    }
}

@Composable
fun WhatNowPromiseAddTopBar(
    modifier: Modifier = Modifier,
    buttonOnClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(64.dp)
                .fillMaxWidth(),
            color = WhatNowTheme.colors.gray50,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp)
                    .height(42.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.leftarrow),
                    contentDescription = null,
                    modifier = Modifier
                        .width(18.dp)
                        .fillMaxHeight()
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 19.dp),
                    text = LocalContext.current.getString(R.string.promise_title),
                    fontSize = 22.sp,
                    color = WhatNowTheme.colors.whatNowBlack,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }
        }
    }

}

@Composable
fun WhatNowPromiseAddBottomBar(
    buttonOnClick: () -> Unit,
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
                        .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)

                ) {
                    Surface(modifier = Modifier
                        .align(Alignment.TopStart)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {

                            }
                        ),
                        color = WhatNowTheme.colors.gray900) {
                        Text(
                            modifier = Modifier.padding(top = 14.5.dp),
                            text = LocalContext.current.getString(R.string.promise_bottom_reset),
                            style = TextStyle(textDecoration = TextDecoration.Underline),
                            textAlign = TextAlign.Center,
                            color = MaterialColors.onPrimary
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(74.dp)
                            .height(56.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = buttonOnClick
                            ),
                        shape = RoundedCornerShape(16.dp),
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