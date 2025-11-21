package com.raihanardila.alfatih.data.repository

import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.data.local.dao.AyahDao
import com.raihanardila.alfatih.data.local.dao.SurahDao
import com.raihanardila.alfatih.data.mapper.toDomain
import com.raihanardila.alfatih.data.util.DataSeeder
import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.model.Surah
import com.raihanardila.alfatih.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuranRepositoryImpl @Inject constructor(
    private val surahDao: SurahDao,
    private val ayahDao: AyahDao,
    private val dataSeeder: DataSeeder
) : QuranRepository {

    private var isInitialized = false

    private suspend fun ensureInitialized() {
        if (!isInitialized) {
            synchronized(this) {
                if (!isInitialized) {
                    dataSeeder.seedDatabase()
                    isInitialized = true
                }
            }
        }
    }

    override fun getAllSurahs(): Flow<Result<List<Surah>>> {
        return surahDao.getAllSurahs()
            .onStart { ensureInitialized() }
            .map<List<com.raihanardila.alfatih.data.local.entity.SurahEntity>, Result<List<Surah>>> { entities ->
                Result.Success(entities.map { it.toDomain() })
            }
            .catch { emit(Result.Error(it)) }
    }

    override fun getSurahByNumber(number: Int): Flow<Result<Surah>> {
        return surahDao.getSurahByNumber(number)
            .onStart { ensureInitialized() }
            .map<com.raihanardila.alfatih.data.local.entity.SurahEntity?, Result<Surah>> { entity ->
                entity?.let { Result.Success(it.toDomain()) }
                    ?: Result.Error(Exception("Surah not found"))
            }
            .catch { emit(Result.Error(it)) }
    }

    override fun getAyahsBySurah(surahNumber: Int): Flow<Result<List<Ayah>>> {
        return ayahDao.getAyahsBySurah(surahNumber)
            .onStart { ensureInitialized() }
            .map<List<com.raihanardila.alfatih.data.local.entity.AyahEntity>, Result<List<Ayah>>> { entities ->
                Result.Success(entities.map { it.toDomain() })
            }
            .catch { emit(Result.Error(it)) }
    }

    override fun getAyahByNumber(ayahNumber: Int): Flow<Result<Ayah>> {
        return ayahDao.getAyahByNumber(ayahNumber)
            .onStart { ensureInitialized() }
            .map<com.raihanardila.alfatih.data.local.entity.AyahEntity?, Result<Ayah>> { entity ->
                entity?.let { Result.Success(it.toDomain()) }
                    ?: Result.Error(Exception("Ayah not found"))
            }
            .catch { emit(Result.Error(it)) }
    }

    override fun searchAyahs(query: String): Flow<Result<List<Ayah>>> {
        return ayahDao.searchAyahs(query)
            .onStart { ensureInitialized() }
            .map<List<com.raihanardila.alfatih.data.local.entity.AyahEntity>, Result<List<Ayah>>> { entities ->
                Result.Success(entities.map { it.toDomain() })
            }
            .catch { emit(Result.Error(it)) }
    }
}
