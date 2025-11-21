package com.raihanardila.alfatih

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raihanardila.alfatih.core.design.theme.AlFatihTheme
import com.raihanardila.alfatih.feature.quran.navigation.HOME_ROUTE
import com.raihanardila.alfatih.feature.quran.navigation.homeScreen
import com.raihanardila.alfatih.feature.quran.navigation.navigateToSurahDetail
import com.raihanardila.alfatih.feature.quran.navigation.surahDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlFatihTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = HOME_ROUTE
                    ) {
                        homeScreen(
                            onSurahClick = { surahNumber ->
                                navController.navigateToSurahDetail(surahNumber)
                            }
                        )

                        surahDetailScreen(
                            onNavigateBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}