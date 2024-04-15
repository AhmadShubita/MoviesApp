package com.ahmadshubita.moviesapp.ui.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


object DatesUtil {

    private const val DAY_FORMAT = "yyyy-MM-dd"

    fun getYear(dateString: String?): String {
        dateString?.let {
            // Parse the date string into a Date object
            val formatter = SimpleDateFormat(DAY_FORMAT, Locale.US)
            val date = formatter.parse(dateString)

            // Create a Calendar instance and set the time to the parsed date
            val calendar = Calendar.getInstance()
            calendar.time = date

            // Get the year from the Calendar instance
            return calendar.get(Calendar.YEAR).toString()
        } ?: return ""
    }
}