package com.example.spacextracker.data.db.entity

import androidx.room.TypeConverter

class IdListConverter {

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