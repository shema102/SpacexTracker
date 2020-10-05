package com.shema102.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shema102.spacextracker.data.db.entity.NextLaunchEntry
import com.shema102.spacextracker.data.db.entity.Payload

@Dao
interface PayloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunchEntry)

    @Query("select * from payload where id = :id")
    fun getPayloadWithId(id: String): LiveData<Payload>
}