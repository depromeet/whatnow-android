package com.depromeet.whatnow.ui.promiseAdd

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.depromeet.whatnow.component.WhatNowPlaceMap
import com.depromeet.whatnow.component.WhatNowSimpleTopBar
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.dialog.promiseResetDialog
import com.depromeet.whatnow.ui.theme.MaterialColors
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PromiseScreen(
    viewModel: PromiseAddViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    val locationMapUi by viewModel.locationMap.collectAsState()
    val locationListUi by viewModel.locationListUi.collectAsState()
    val locationListData by viewModel.locationListData.collectAsState()

    val showDialog by viewModel.promiseResetPopup.collectAsState()

    var screenState by remember { mutableStateOf(PromiseScreenState()) }

    val selectedCalendar = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf("") }
    val selectedPlace = remember { mutableStateOf("") }
    val selectedPlaceLatitude = remember { mutableStateOf(0.0) }
    val selectedPlaceLongitude = remember { mutableStateOf(0.0) }

    // 서버용 포맷 데이터
    val calendarData = remember { mutableStateOf("") }
    val timeData = remember { mutableStateOf("") }

    val buttonText = remember { mutableStateOf("다음") }


    Scaffold(
        topBar = {
            WhatNowSimpleTopBar(
                onBack = onBack,
                titleRes = R.string.promise_title,
            )
        },
        bottomBar = {
            WhatNowPromiseAddBottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                buttonOnClick = {
                    if (!screenState.isSetDateValue && !screenState.isClockVisible && !screenState.isPlaceVisible) {
                        screenState = screenState.copy(
                            isSetDateValue = true,
                            isClockVisible = true,
                            isClockDataSet = true
                        )
                    } else if (screenState.isSetDateValue && screenState.isClockVisible && !screenState.isPlaceVisible) {
                        screenState = screenState.copy(
                            isClockVisible = false,
                            isPlaceVisible = true,
                            isPlaceDataSet = true,
                        )
                    } else if (screenState.isSetDateValue && screenState.isClockDataSet && screenState.isPlaceDataSet && selectedPlace.value == "") {
//                        if (selectedPlace.value != "") {
//                            screenState = screenState.copy(
//                                isClockVisible = false,
//                                isPlaceVisible = false
//                            )
//                            buttonText.value = "만들기"
//                            viewModel.getLocationMap(selectedPlaceLatitude.value, selectedPlaceLongitude.value)
//                        }
                    } else {
                        viewModel.getPromiseDetail(
                            calendarData.value,
                            timeData.value,
                            selectedPlace.value,
                            selectedPlaceLatitude.value,
                            selectedPlaceLongitude.value
                        )
                    }
                },
                resetOnClick = {
                    viewModel.promiseReset(true)
                },
                buttonText = buttonText.value
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                if (screenState.isSetDateValue) {
                    setMakeBox(
                        onClick = {
                            selectedTime.value = ""
                            screenState = screenState.copy(
                                isSetDateValue = false,
                                isPlaceVisible = false,
                                isPlaceDataSet = false,
                                isClockVisible = false,
                                isClockDataSet = false,
                            )
                        },
                        R.string.promise_calendar,
                        null,
                        R.drawable.calendar,
                        true,
                        selectedCalendar.value
                    )
                    buttonText.value = "다음"
                } else {
                    viewModel.getTurnOffLocationMap()
                    viewModel.turnOffLocationListUi()
                    Calendar(
                        onDateChanged = {
                            selectedCalendar.value = it
                        },
                        onDateData = {
                            calendarData.value = it
                        }
                    )
                    screenState = screenState.copy(isClockDataSet = false)
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (!screenState.isClockDataSet && !screenState.isClockVisible) {
                    setMakeBox(
                        onClick = {
                            selectedPlace.value = ""
                            screenState = screenState.copy(
                                isSetDateValue = true,
                                isClockVisible = true,
                                isClockDataSet = true
                            )
                        },
                        R.string.promise_time,
                        R.string.promise_time_msg,
                        R.drawable.clock2,
                        false, null
                    )
                    buttonText.value = "다음"
                } else if (screenState.isClockDataSet && !screenState.isClockVisible) {
                    setMakeBox(
                        onClick = {
                            viewModel.getTurnOffLocationMap()
                            viewModel.turnOffLocationListUi()
                            screenState = screenState.copy(
                                isSetDateValue = true,
                                isClockVisible = true,
                                isClockDataSet = true,
                                isPlaceDataSet = false,
                                isPlaceVisible = false
                            )
                        },
                        R.string.promise_time,
                        null,
                        R.drawable.clock2,
                        true, selectedTime.value
                    )
                } else {
                    setClock(
                        onTimeChanged = {
                            selectedTime.value = it
                        },
                        onTimeData = {
                            timeData.value = it
                        }
                    )
                    screenState = screenState.copy(isPlaceDataSet = false)
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (!screenState.isPlaceDataSet && !screenState.isPlaceVisible) {
                    setMakeBox(
                        onClick = {
                            screenState = screenState.copy(
                                isSetDateValue = true,
                                isClockVisible = false,
                                isClockDataSet = true,
                                isPlaceVisible = true,
                                isPlaceDataSet = true
                            )
                        },
                        R.string.promise_place,
                        R.string.promise_place_msg,
                        R.drawable.map,
                        false, null
                    )
                    buttonText.value = "다음"
                } else if (screenState.isPlaceDataSet && !screenState.isPlaceVisible) {
                    setMakeBox(
                        onClick = {
                            screenState = screenState.copy(
                                isSetDateValue = true,
                                isClockVisible = false,
                                isClockDataSet = true,
                                isPlaceVisible = true
                            )
                        },
                        R.string.promise_place,
                        null,
                        R.drawable.map,
                        true, selectedPlace.value
                    )
                    buttonText.value = "만들기"
                } else {
                    PlaceList(viewModel = viewModel)
                }
            }

            Spacer(modifier = Modifier.height(18.dp)) // 간격 설정

            if (locationMapUi) {
                WhatNowPlaceMap(modifier = Modifier, context = context, viewModel = viewModel)
            }

            PlaceList(locationListUi, locationListData, onClick = { place ->
                selectedPlace.value = place.placeAddress
                selectedPlaceLatitude.value = place.latitude
                selectedPlaceLongitude.value = place.longitude

                viewModel.turnOffLocationListUi()

                screenState = screenState.copy(
                    isClockVisible = false,
                    isPlaceVisible = false
                )
                buttonText.value = "만들기"
                viewModel.getLocationMap(selectedPlaceLatitude.value, selectedPlaceLongitude.value)
            })
        }
    }
    if (showDialog) {
        promiseResetDialog(onDismiss = {
            viewModel.promiseReset(false)
        }, okClick = {
            viewModel.promiseReset(false)
            viewModel.getTurnOffLocationMap()
            viewModel.turnOffLocationListUi()

            selectedCalendar.value = ""
            selectedTime.value = ""
            selectedPlace.value = ""
            screenState = screenState.copy(
                isSetDateValue = false,
                isPlaceVisible = false,
                isClockVisible = false,
                isClockDataSet = false,
                isPlaceDataSet = false
            )
            buttonText.value = "다음"
        })
    }
}

@Composable
fun PlaceList(
    locationListUi: Boolean,
    locationListData: List<PromiseAddPlace>,
    onClick: (PromiseAddPlace) -> Unit
) {
    if (locationListUi) {
        Column {
            locationListData.forEach { item ->
                SearchPlaceList(place = item, onClick = { onClick(item) })
            }
        }
    }
}


data class PromiseScreenState(
    val isSetDateValue: Boolean = false,
    val isClockDataSet: Boolean = false,
    val isPlaceDataSet: Boolean = false,
    val isClockVisible: Boolean = false,
    val isPlaceVisible: Boolean = false,
)

@Composable
fun Greeting(resId: Int, textSize: Int, textColor: Color) {
    Text(
        text = LocalContext.current.getString(resId),
        fontSize = textSize.sp,
        color = textColor
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(onDateChanged: (String) -> Unit, onDateData: (String) -> Unit) {
    WhatNowTheme {
        Box(
            modifier = Modifier
                .padding(start = 15.dp)
                .width(350.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            val context = LocalContext.current

            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    val themedContext = ContextThemeWrapper(context, R.style.CreateProfileTheme)
                    val view = LayoutInflater.from(themedContext)
                        .inflate(R.layout.item_promise_calendar, null)
                    val datePicker = view.findViewById<DatePicker>(R.id.datePicker1)

                    // 현재 날짜로 DatePicker 초기화
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    datePicker.updateDate(year, month, day)

                    // 날짜를 바꾸지 않으면 현재 날짜 입력
                    val selectedDateString = String.format("%d월 %d일 약속", month, day)
                    onDateChanged(selectedDateString)

                    datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
                        Log.d("yw", "year = $year")
                        Log.d("yw", "monthOfYear = $monthOfYear")
                        Log.d("yw", "dayOfMonth = $dayOfMonth")

                        val formattedMonth =
                            if (monthOfYear >= 10) monthOfYear.toString() else "0$monthOfYear"
                        val formattedDay =
                            if (dayOfMonth >= 10) dayOfMonth.toString() else "0$dayOfMonth"
                        val selectedDateData = "$year-$formattedMonth-$formattedDay"

                        val selectedDateString =
                            String.format("%d월 %d일 약속", monthOfYear, dayOfMonth)

                        onDateData(selectedDateData)
                        onDateChanged(selectedDateString)
                    }

                    view
                }
            )
        }
    }
}

@Composable
fun PlaceList(viewModel: PromiseAddViewModel) {
    PlaceEdit(
        onPlaceChanged = {
            viewModel.getItemList(it)
        },
        R.string.promise_place_select,
        R.string.promise_place_msg,
        R.drawable.whitemap
    )

}

@Composable
fun PlaceEdit(onPlaceChanged: (String) -> Unit, titleResId: Int, messageResId: Int, iconRes: Int) {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(74.dp)
            .background(
                color = WhatNowTheme.colors.whatNowBlack,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                text = LocalContext.current.getString(titleResId),
                style = WhatNowTheme.typography.body4,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

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
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BasicTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            onPlaceChanged(text)
                        },

                        textStyle = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp // 원하는 크기로 설정
                        ),
                        decorationBox = {
                            Box(
                                modifier = Modifier.padding(1.dp)
                            ) {
                                if (text.isBlank()) {
                                    Text(
                                        text = LocalContext.current.getString(R.string.promise_place_msg),
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                    )
                                }
                                it()
                            }
                        },
                        cursorBrush = SolidColor(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchPlaceList(place: PromiseAddPlace, onClick: () -> Unit) {
    val selectedText = remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .height(72.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(containerColor = WhatNowTheme.colors.gray50)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    PromiseLocationList(place.placeTitle)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = WhatNowTheme.colors.gray700
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        place.placeAddress.let {
                            Text(
                                text = it,
                                style = WhatNowTheme.typography.caption1,
                                color = WhatNowTheme.colors.gray700,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun setClock(onTimeChanged: (String) -> Unit, onTimeData: (String) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        AndroidView(
            factory = { context ->
                val themedContext = ContextThemeWrapper(context, R.style.CustomTimePicker)
                val view =
                    LayoutInflater.from(themedContext).inflate(R.layout.item_promise_clock, null)

                val timePicker = view.findViewById<TimePicker>(R.id.timePicker)

                // 바꾸지 않고 바로 하면 현재 시각 입력
                onTimeChanged(clockFormatting(timePicker.hour, timePicker.minute))

                timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

                    val hourString = if (hourOfDay >= 10) hourOfDay.toString() else "0$hourOfDay"
                    val minuteString = if (minute >= 10) minute.toString() else "0$minute"
                    val onTimeData = "$hourString:$minuteString"


                    onTimeData(onTimeData)
                    onTimeChanged(clockFormatting(hourOfDay, minute))
                }
                view
            }
        )
    }
}

// 시각 포매팅
fun clockFormatting(hourOfDay: Int, minute: Int): String {
    val formattedHour = if (hourOfDay >= 12) {
        val hour = if (hourOfDay > 12) hourOfDay - 12 else hourOfDay
        String.format("%02d", hour)
    } else {
        String.format("%02d", hourOfDay)
    }

    val formattedMinute = String.format("%02d", minute)
    val amPm = if (hourOfDay >= 12) "오후" else "오전"

    return "$amPm $formattedHour 시 $formattedMinute 분"
}

@Composable
fun setMakeBox(
    onClick: () -> Unit,
    titleResId: Int,
    messageResId: Int?,
    iconRes: Int,
    isSelected: Boolean,
    message: String?,
) {

    val backgroundColor = if (isSelected) MaterialColors.onPrimary else WhatNowTheme.colors.gray50
    val borderColor = if (isSelected) MaterialColors.onPrimary else WhatNowTheme.colors.whatNowBlack

    if (!isSelected) {
        IncompleteContent(titleResId)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .border(
                width = if (isSelected) 0.dp else 0.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp)) // 간격 설정

            if (messageResId == null && message != null) {
                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = WhatNowTheme.colors.gray900
                )
            } else {
                Greeting(messageResId!!, 14, WhatNowTheme.colors.gray500)
            }

        }
    }
}

@Composable
fun IncompleteContent(titleResId: Int) {
    Greeting(titleResId, 12, WhatNowTheme.colors.gray900)
    Spacer(modifier = Modifier.height(6.dp))
}


@Composable
fun WhatNowPromiseAddBottomBar(
    resetOnClick: () -> Unit,
    buttonOnClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonText: String,
) {
    val bottomPanelHeight = 80.dp

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
                            modifier = Modifier
                                .padding(top = 14.5.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                    onClick = resetOnClick
                                ),
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
                                text = buttonText,
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


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(device = Devices.PIXEL_2)
@Composable
fun DefaultPreview() {
    WhatNowTheme {
        PromiseScreen(onBack = {})
    }
}