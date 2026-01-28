package com.poc.core.presentation.format

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

object DateFormat {
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

        val formattedDateTime = messageDateTime.format(
            LocalDateTime.Format {
                monthName(MonthNames.ENGLISH_ABBREVIATED)
                char(' ')
                day()
                char(' ')
                year()
                chars(", ")
                chars(formattedTime)
            }
        )
        return formattedDateTime


    }
}