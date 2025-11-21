package com.raihanardila.alfatih.data.di

import android.content.Context
import androidx.room.Room
import com.raihanardila.alfatih.data.local.dao.AyahDao
import com.raihanardila.alfatih.data.local.dao.SurahDao
import com.raihanardila.alfatih.data.local.database.QuranDatabase
import com.raihanardila.alfatih.data.repository.QuranRepositoryImpl
import com.raihanardila.alfatih.domain.repository.QuranRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideQuranDatabase(
        @ApplicationContext context: Context
    ): QuranDatabase {
        return Room.databaseBuilder(
            context,
            QuranDatabase::class.java,
            "quran_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSurahDao(database: QuranDatabase): SurahDao {
        return database.surahDao()
    }

    @Provides
    @Singleton
    fun provideAyahDao(database: QuranDatabase): AyahDao {
        return database.ayahDao()
    }

    @Provides
    @Singleton
    fun provideQuranRepository(
        surahDao: SurahDao,
        ayahDao: AyahDao
    ): QuranRepository {
        return QuranRepositoryImpl(surahDao, ayahDao)
    }
}
