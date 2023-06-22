package com.example.newsapp.core

import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun dateToTimeFormat(oldStringDate: String): String {
        val p = PrettyTime(Locale(country))
        var isTime = ""
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.getDefault()
            )
            val date = sdf.parse(oldStringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    fun dateFormat(oldStringDate: String): String {
        var newDate = ""
        val dateFormat = SimpleDateFormat(
            "E, d MMM yyyy", Locale(
                country
            )
        )
        newDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldStringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldStringDate
        }
        return newDate
    }

    val country: String
        get() {
            val locale = Locale("uk", "UA")
            val country = locale.country.toString()
            return country.lowercase(Locale.getDefault())
        }
}