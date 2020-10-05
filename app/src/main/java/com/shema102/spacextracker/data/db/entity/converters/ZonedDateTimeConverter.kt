package com.shema102.spacextracker.data.db.entity.converters

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeConverter {
    @TypeConverter
    fun fromZonedDateTime(dateTime: ZonedDateTime): String{
        return dateTime.toString()
    }

    @TypeConverter
    fun toZonedDateTime(string: String): ZonedDateTime{
        return ZonedDateTime.parse(string)
    }
}