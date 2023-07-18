package com.depromeet.whatnow.ui.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DateDisplayUtil {
    fun LocalDateTime.toRelativeDateString(): String {
        val today = LocalDateTime.now()

        return when {
            plusYears(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.YEARS.between(this, today)}년 전"
            }

            plusMonths(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.MONTHS.between(this, today)}달 전"
            }

            plusWeeks(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.WEEKS.between(this, today)}주 전"
            }

            plusDays(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.DAYS.between(this, today)}일 전"
            }

            plusHours(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.HOURS.between(this, today)}시간 전"
            }

            plusMinutes(1).isBeforeOrEqual(today) -> {
                "${ChronoUnit.MINUTES.between(this, today)}분 전"
            }

            else -> {
                "NEW"
            }
        }
    }

    private fun LocalDateTime.isBeforeOrEqual(other: LocalDateTime) =
        isEqual(other) || isBefore(other)
}
