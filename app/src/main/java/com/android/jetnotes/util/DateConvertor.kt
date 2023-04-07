package com.android.jetnotes.util

import androidx.room.TypeConverter
import java.util.*

class DateConvertor {
    @TypeConverter
    fun timeStampFromDate(date: Date) : Long{
        return date.time
    }
    @TypeConverter
    fun dateFromTimeStamp(timeStamp : Long) : Date?{
        return Date(timeStamp)
    }
}