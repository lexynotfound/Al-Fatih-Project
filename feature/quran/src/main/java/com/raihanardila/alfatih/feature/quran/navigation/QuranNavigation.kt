package com.raihanardila.alfatih.feature.quran.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.raihanardila.alfatih.feature.quran.detail.SurahDetailScreen
import com.raihanardila.alfatih.feature.quran.home.HomeScreen

const val HOME_ROUTE = "home"
const val SURAH_DETAIL_ROUTE = "surah/{surahNumber}"

fun NavController.navigateToHome() {
    navigate(HOME_ROUTE) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToSurahDetail(surahNumber: Int) {
    navigate("surah/$surahNumber")
}

fun NavGraphBuilder.homeScreen(
    onSurahClick: (Int) -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeScreen(onSurahClick = onSurahClick)
    }
}

fun NavGraphBuilder.surahDetailScreen(
    onNavigateBack: () -> Unit
) {
    composable(
        route = SURAH_DETAIL_ROUTE,
        arguments = listOf(
            navArgument("surahNumber") {
                type = NavType.StringType
            }
        )
    ) {
        SurahDetailScreen(onNavigateBack = onNavigateBack)
    }
}
