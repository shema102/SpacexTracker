package com.shema102.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shema102.spacextracker.data.db.entity.NEXT_LAUNCH_ID
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.entity.converters.ZonedDateTimeConverter
import org.threeten.bp.ZonedDateTime


@Dao
@TypeConverters(ZonedDateTimeConverter::class)
interface NextLaunchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunchEntry)

    @Query("select * from next_launch where `key` = $NEXT_LAUNCH_ID")
    fun getNextLaunch(): LiveData<NextLaunchEntry>

    @Query("select last_update from next_launch where `key` = $NEXT_LAUNCH_ID")
    fun getNextLaunchLastUpdateTime(): ZonedDateTime?
}