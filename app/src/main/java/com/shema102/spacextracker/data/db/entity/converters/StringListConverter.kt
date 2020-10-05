package com.shema102.spacextracker.data.db.entity.converters

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromIdList(list: List<String>?): String {
        return list?.joinToString(separator = ",") ?: "NULL"
    }
    @TypeConverter
    fun toIdList(data: String): List<String>? {
        return if (data != "NULL") {
            data.split(delimiters = arrayOf(","))
        } else {
            null
        }
    }
}