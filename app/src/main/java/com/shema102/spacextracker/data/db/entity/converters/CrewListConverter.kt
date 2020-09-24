package com.shema102.spacextracker.data.db.entity.converters

import androidx.room.TypeConverter
import com.shema102.spacextracker.data.db.entity.Crew
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CrewListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCrewList(crew: List<Crew>): String {
        return gson.toJson(crew)
    }

    @TypeConverter
    fun toCrewList(data: String): List<Crew> {
        val listType = genericType<List<Crew>>()

        return gson.fromJson(data, listType)
    }

    inline fun <reified T> genericType() = object : TypeToken<T>() {}.type
}