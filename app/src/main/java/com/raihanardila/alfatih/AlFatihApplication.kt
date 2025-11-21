package com.raihanardila.alfatih

import android.app.Application
import com.raihanardila.alfatih.initializer.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AlFatihApplication : Application() {

    @Inject
    lateinit var databaseInitializer: DatabaseInitializer

    override fun onCreate() {
        super.onCreate()
        databaseInitializer.initialize()
    }
}
