package com.raihanardila.alfatih.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raihanardila.alfatih.data.local.entity.AyahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AyahDao {
    @Query("SELECT * FROM ayahs WHERE surahNumber = :surahNumber ORDER BY numberInSurah ASC")
    fun getAyahsBySurah(surahNumber: Int): Flow<List<AyahEntity>>

    @Query("SELECT * FROM ayahs WHERE number = :ayahNumber")
    fun getAyahByNumber(ayahNumber: Int): Flow<AyahEntity?>

    @Query("SELECT * FROM ayahs WHERE text LIKE '%' || :query || '%' OR textArabic LIKE '%' || :query || '%'")
    fun searchAyahs(query: String): Flow<List<AyahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyah(ayah: AyahEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyahs(ayahs: List<AyahEntity>)

    @Query("DELETE FROM ayahs")
    suspend fun deleteAllAyahs()
}
