package com.shema102.spacextracker.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shema102.spacextracker.data.db.entity.Crew
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry

@Dao
interface CrewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunchEntry)

    @Query("select * from crew where id = :id")
    fun getNextLaunch(id: String): LiveData<Crew>
}