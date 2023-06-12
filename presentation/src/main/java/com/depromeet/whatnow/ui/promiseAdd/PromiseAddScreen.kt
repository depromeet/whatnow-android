package com.depromeet.whatnow.ui.promiseAdd

import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import java.util.*

@Composable
fun PromiseScreen(viewModel: PromiseAddViewModel) {
    val context = LocalContext.current
}

@Composable
fun test(){
    Surface(color = Color.White) {
        Box(modifier = Modifier.zIndex(1f)){
            Text(text = "날짜 선택")
        }
    }
}

@Preview(showBackground = false, widthDp = 320)
@Composable
fun DefaultPreview() {
    Surface(color = WhatNowTheme.colors.gray200) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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

            Spacer(modifier = Modifier.height(12.dp))

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
    }
}