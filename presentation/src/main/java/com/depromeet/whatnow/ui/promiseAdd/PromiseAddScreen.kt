package com.depromeet.whatnow.ui.promiseAdd

import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.depromeet.whatnow.component.WhatNowPromiseAddBottomBar
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.util.*

@Composable
fun PromiseScreen(viewModel: PromiseAddViewModel) {
    val context = LocalContext.current
}

@Composable
fun Greeting(text : String, textSize: Int, textColor: Color){
    Text(
        text = text,
        fontSize = textSize.sp,
        color = textColor
    )
}


@Composable
fun setCalendar(){
    Box(
        modifier = Modifier
            .padding(22.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ){
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val view = LayoutInflater.from(context).inflate(R.layout.item_promise_calendar, null)
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
fun setClock(){
    Box(
        modifier = Modifier
            .padding(22.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ){
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                LayoutInflater.from(context).inflate(R.layout.item_promise_clock, null)
            }
        )

    }
}

@Composable
fun setMakeBox(onClick: () -> Unit,title:String , msg:String , img:Int){
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
                .clickable(onClick = onClick)
        ) {
            Greeting(title,12,WhatNowTheme.colors.gray900)

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
                        .clickable {

                        }
                ) {
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 6.dp)
                    )
                    Greeting(msg,14,WhatNowTheme.colors.gray500)
                }
            }
        }
    }
}


@Preview(showBackground = false, widthDp = 320)
@Composable
fun DefaultPreview() {

    var isClockVisible by remember { mutableStateOf(false) }

    Surface(color = WhatNowTheme.colors.gray200) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            setCalendar()

            if(!isClockVisible){
                setMakeBox(onClick = { isClockVisible = true},"시간","몇시에 만날까요?",R.drawable.clock)
            }else{
                setClock()
            }

            Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            bottomBar = {
                WhatNowPromiseAddBottomBar(
                    modifier = Modifier
                )
            }
            ){
                it.calculateBottomPadding()
            }

//            setMakeBox(onClick={},"장소","어디에사 만날까요?",R.drawable.map)
//
//
//            Spacer(modifier = Modifier.height(12.dp))
//
            setClock()
        }
    }
}


@Composable
fun FixedBottomView() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        // 고정적인 뷰 내용
        // ...
    }
}





