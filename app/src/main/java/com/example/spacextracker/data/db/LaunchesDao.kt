package com.example.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacextracker.data.db.entity.Launch
import com.example.spacextracker.data.db.entity.NEXT_LAUNCH_ID
import com.example.spacextracker.data.db.entity.NextLaunch

@Dao
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunch)

    @Query("select * from launch_entry where id = :id")
    fun getLaunchWithId(id: String): LiveData<Launch>

    @Query("select * from launch_entry")
    fun getAllLaunches(): LiveData<List<Launch>>
}