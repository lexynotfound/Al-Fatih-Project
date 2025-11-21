package com.raihanardila.alfatih.domain.repository

import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun getAllSurahs(): Flow<Result<List<Surah>>>
    fun getSurahByNumber(number: Int): Flow<Result<Surah>>
    fun getAyahsBySurah(surahNumber: Int): Flow<Result<List<Ayah>>>
    fun getAyahByNumber(ayahNumber: Int): Flow<Result<Ayah>>
    fun searchAyahs(query: String): Flow<Result<List<Ayah>>>
}
