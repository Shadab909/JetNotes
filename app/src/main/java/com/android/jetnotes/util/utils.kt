package com.android.jetnotes.util

import java.text.SimpleDateFormat
import java.util.*

fun formateDate(time : Long) : String{
    val date = Date(time)
    val format = SimpleDateFormat(
        "EEE, d MMM",
        Locale.getDefault()
    )
    return format.format(date)
}