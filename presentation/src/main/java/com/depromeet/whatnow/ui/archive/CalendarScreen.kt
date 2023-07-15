package com.depromeet.whatnow.ui.archive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.depromeet.whatnow.ui.R
import com.depromeet.whatnow.ui.model.Promise
import com.depromeet.whatnow.ui.theme.Black
import com.depromeet.whatnow.ui.theme.Roboto
import com.depromeet.whatnow.ui.theme.WhatNowTheme
import com.depromeet.whatnow.ui.theme.White
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    promises: List<Promise>,
    onClickItem: (List<Promise>, Int) -> Unit
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember {
        if (promises.isNotEmpty()) YearMonth.from(promises.minOf { it.datetime })
        else currentMonth.minusMonths(2)
    }
    val endMonth = remember {
        if (promises.isNotEmpty()) YearMonth.from(promises.maxOf { it.datetime })
        else currentMonth.plusMonths(2)
    }
    val daysOfWeek = remember { daysOfWeek() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = daysOfWeek.first()
    )

    VerticalCalendar(
        state = state,
        dayContent = { Day(day = it, promises = promises, onClickItem = onClickItem) },
        monthHeader = {
            YearMonthTitle(
                calendarMonth = it,
                modifier = Modifier.padding(12.dp)
            )
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
        },
        monthFooter = { Spacer(modifier = Modifier.height(24.dp)) },
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    )

}

@Composable
fun YearMonthTitle(
    calendarMonth: CalendarMonth,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(
                id = R.string.month,
                calendarMonth.yearMonth.monthValue,
            ),
            style = WhatNowTheme.typography.body2,
            color = WhatNowTheme.colors.gray900
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = calendarMonth.yearMonth.year.toString(),
            style = WhatNowTheme.typography.body2,
            color = WhatNowTheme.colors.gray900
        )
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                style = WhatNowTheme.typography.body4,
                color = WhatNowTheme.colors.gray800
            )
        }
    }
}

@Composable
fun Day(
    day: CalendarDay,
    promises: List<Promise>,
    onClickItem: (List<Promise>, Int) -> Unit
) {
    val promise = promises.firstOrNull { it.datetime.toLocalDate() == day.date }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { if (promise != null) onClickItem(promises, promises.indexOf(promise)) },
        contentAlignment = Alignment.Center
    ) {
        if (day.position == DayPosition.MonthDate) promise?.let {
            AsyncImage(
                model = it.imageUrls.first(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )
            Surface(
                color = Black.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {}
        }
        Text(
            text = day.date.dayOfMonth.toString(),
            style = WhatNowTheme.typography.body3,
            color = if (day.position == DayPosition.MonthDate) {
                if (promise == null) WhatNowTheme.colors.gray400 else White
            } else {
                Color.Transparent
            },
            fontFamily = Roboto
        )
    }
}
