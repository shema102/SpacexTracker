package com.example.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacextracker.data.db.entity.LaunchEntry
import com.example.spacextracker.data.db.entity.NextLaunch

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunch)

    @Query("select * from launch_entry where id = :id")
    fun getLaunchWithId(id: String): LiveData<LaunchEntry>

    @Query("select * from launch_entry")
    fun getAllLaunches(): LiveData<List<LaunchEntry>>
}