package com.raihanardila.alfatih.initializer

import android.content.Context
import com.raihanardila.alfatih.data.util.DataSeeder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseInitializer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSeeder: DataSeeder
) {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun initialize() {
        applicationScope.launch {
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val isDataSeeded = sharedPreferences.getBoolean("is_data_seeded", false)

            if (!isDataSeeded) {
                dataSeeder.seedDatabase()
                sharedPreferences.edit().putBoolean("is_data_seeded", true).apply()
            }
        }
    }
}
