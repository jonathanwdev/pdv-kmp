package com.poc.core.presentation.format

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant

object DateFormat {
    fun formatTransactionDateTime(instant: Instant): String {
        val timezone = TimeZone.currentSystemDefault()
        val messageDateTime = instant.toLocalDateTime(timezone)

        val formattedDateTime = messageDateTime.format(
            LocalDateTime.Format {
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                day()
                char(' ')
                year()
                chars(", ")
                chars(formatTransactionTime(instant))
            }
        )
        return formattedDateTime


    }

    fun formatTransactionDateWithDay(instant: Instant): String {
        val timezone = TimeZone.currentSystemDefault()
        val messageDateTime = instant.toLocalDateTime(timezone)

        val formattedDateTime = messageDateTime.format(
            LocalDateTime.Format {
                dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
                char(' ')
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                day()
                chars(", ")
                year()
            }
        )
        return formattedDateTime

    }

    fun formatTransactionTime(instant: Instant): String {
        val timezone = TimeZone.currentSystemDefault()
        val messageDateTime = instant.toLocalDateTime(timezone)

        val formattedTime = messageDateTime.format(
            format = LocalDateTime.Format {
                amPmHour()
                char(':')
                minute()
                amPmMarker("AM", "PM")
            }
        )

        return formattedTime
    }

    fun dateLessOneDay(defaultDays: Int = 1): Long {
        return Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .minus(defaultDays, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    }
}