package com.example.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacextracker.data.db.entity.NEXT_LAUNCH_ID
import com.example.spacextracker.data.db.entity.NextLaunch
import com.example.spacextracker.data.db.entity.Payload

@Dao
interface PayloadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunch)

    @Query("select * from payload where id = :id")
    fun getPayloadWithId(id: String): LiveData<Payload>
}