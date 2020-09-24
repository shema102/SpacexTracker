package com.shema102.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shema102.spacextracker.data.db.entity.ROADSTER_ID
import com.shema102.spacextracker.data.db.entity.RoadsterEntry
import com.shema102.spacextracker.data.db.unitlocalized.ImperialRoadster
import com.shema102.spacextracker.data.db.unitlocalized.MetricRoadster

@Dao
interface RoadsterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(roadsterEntry: RoadsterEntry)

    @Query("select * from roadster where `key` = $ROADSTER_ID")
    fun getRoadsterMetric(): LiveData<MetricRoadster>

    @Query("select * from roadster where `key` = $ROADSTER_ID")
    fun getRoadsterImperial(): LiveData<ImperialRoadster>
}