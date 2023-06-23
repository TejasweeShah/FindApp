package com.codewithteju.findapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codewithteju.findapp.domain.model.Advertisement
import kotlinx.coroutines.flow.Flow

@Dao
interface FindAppDao {

    @Query("SELECT * FROM Advertisement")
    fun getAllAds(): Flow<List<Advertisement>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAd(adv: Advertisement)

    @Delete
    suspend fun deleteAd(adv: Advertisement)

}