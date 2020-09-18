package com.example.spacextracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacextracker.data.db.entity.NEXT_LAUNCH_ID
import com.example.spacextracker.data.db.entity.NextLaunch

@Dao
interface NextLaunchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(launch: NextLaunch)

    @Query("select * from next_launch where id = $NEXT_LAUNCH_ID")
    fun getNextLaunch(): LiveData<NextLaunch>
}