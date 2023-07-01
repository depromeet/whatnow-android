package com.depromeet.whatnow.ui.archive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.depromeet.whatnow.ui.model.Promise
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.YearMonth

@Composable
fun CalendarScreen(
    promises: List<Promise>,
    onClickItem: (List<Promise>, Int) -> Unit
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { YearMonth.from(promises.minOf { it.datetime }) }
    val endMonth = remember { YearMonth.from(promises.maxOf { it.datetime }) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    VerticalCalendar(
        state = state,
        dayContent = { Day(it) }
    )
}

@Composable
fun Day(day: CalendarDay) {
    Box(
        modifier = Modifier.aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}
