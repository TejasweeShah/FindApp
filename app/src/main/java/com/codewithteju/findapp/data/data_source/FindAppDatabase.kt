package com.codewithteju.findapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codewithteju.findapp.domain.model.Advertisement

@Database(
    entities = [Advertisement::class],
    version = 1
)
abstract class FindAppDatabase : RoomDatabase() {

    abstract val findAppDao: FindAppDao

    companion object {
        const val DATABASE_NAME = "findapp_db"
    }


}