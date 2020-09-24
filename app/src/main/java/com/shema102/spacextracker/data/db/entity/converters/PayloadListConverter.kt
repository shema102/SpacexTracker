package com.shema102.spacextracker.data.db.entity.converters

import androidx.room.TypeConverter
import com.shema102.spacextracker.data.db.entity.Payload
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PayloadListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromPayloadList(payloads: List<Payload>): String{
        return gson.toJson(payloads)
    }
    @TypeConverter
    fun toPayloadList(data: String): List<Payload>{
        val listType = genericType<List<Payload>>()

        return gson.fromJson(data, listType)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}