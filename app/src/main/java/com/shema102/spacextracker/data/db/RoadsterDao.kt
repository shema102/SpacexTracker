package com.shema102.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shema102.spacextracker.data.db.entity.ROADSTER_ID
import com.shema102.spacextracker.data.db.entity.RoadsterEntry
import com.shema102.spacextracker.data.db.entity.converters.ZonedDateTimeConverter
import com.shema102.spacextracker.data.db.unitlocalized.ImperialRoadster
import com.shema102.spacextracker.data.db.unitlocalized.MetricRoadster
import org.threeten.bp.ZonedDateTime

@Dao
@TypeConverters(ZonedDateTimeConverter::class)
interface RoadsterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(roadsterEntry: RoadsterEntry)

    @Query("select * from roadster where `key` = $ROADSTER_ID")
    fun getRoadsterMetric(): LiveData<MetricRoadster>

    @Query("select * from roadster where `key` = $ROADSTER_ID")
    fun getRoadsterImperial(): LiveData<ImperialRoadster>

    @Query("select last_update from roadster where `key` = $ROADSTER_ID")
    fun getRoadsterLastUpdateTime(): ZonedDateTime?
}