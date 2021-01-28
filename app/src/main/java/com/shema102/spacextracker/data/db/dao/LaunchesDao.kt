package com.shema102.spacextracker.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shema102.spacextracker.data.db.entity.LaunchEntry
import com.shema102.spacextracker.data.db.entity.NEXT_LAUNCH_ID
import com.shema102.spacextracker.data.db.entity.converters.ZonedDateTimeConverter
import org.threeten.bp.ZonedDateTime

@Dao
@TypeConverters(ZonedDateTimeConverter::class)
interface LaunchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: List<LaunchEntry>)

    @Query("select * from launch_entry where id = :id")
    fun getLaunchWithId(id: String): LiveData<LaunchEntry>

    @Query("select * from launch_entry")
    fun getAllLaunches(): LiveData<List<LaunchEntry>>

    @Query("select last_update from launch_entry order by random() limit 1")
    fun getRandomLaunchLastUpdateTime(): ZonedDateTime?
}