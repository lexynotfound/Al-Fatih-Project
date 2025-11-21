package com.raihanardila.alfatih.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raihanardila.alfatih.data.local.dao.AyahDao
import com.raihanardila.alfatih.data.local.dao.SurahDao
import com.raihanardila.alfatih.data.local.entity.AyahEntity
import com.raihanardila.alfatih.data.local.entity.SurahEntity

@Database(
    entities = [SurahEntity::class, AyahEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuranDatabase : RoomDatabase() {
    abstract fun surahDao(): SurahDao
    abstract fun ayahDao(): AyahDao
}
