package com.shema102.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shema102.spacextracker.data.db.entity.LaunchEntry

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: List<LaunchEntry>)

    @Query("select * from launch_entry where id = :id")
    fun getLaunchWithId(id: String): LiveData<LaunchEntry>

    @Query("select * from launch_entry")
    fun getAllLaunches(): LiveData<List<LaunchEntry>>
}