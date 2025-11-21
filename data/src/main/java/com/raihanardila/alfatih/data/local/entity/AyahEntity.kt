package com.raihanardila.alfatih.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ayahs")
data class AyahEntity(
    @PrimaryKey val number: Int,
    val numberInSurah: Int,
    val text: String,
    val textArabic: String,
    val surahNumber: Int,
    val juz: Int,
    val page: Int
)
