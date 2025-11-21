package com.raihanardila.alfatih.feature.quran.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raihanardila.alfatih.core.design.theme.Dimensions
import com.raihanardila.alfatih.core.ui.components.AlFatihTopAppBar
import com.raihanardila.alfatih.core.ui.components.EmptyState
import com.raihanardila.alfatih.core.ui.components.ErrorState
import com.raihanardila.alfatih.core.ui.components.LoadingIndicator
import com.raihanardila.alfatih.core.ui.components.SurahCard
import com.raihanardila.alfatih.domain.model.Surah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSurahClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AlFatihTopAppBar(
                title = "Al-Fatih Quran"
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is HomeUiState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is HomeUiState.Success -> {
                if (state.surahs.isEmpty()) {
                    EmptyState(
                        title = "No Surahs Found",
                        message = "There are no surahs available at the moment.",
                        icon = Icons.Default.MenuBook,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                } else {
                    SurahList(
                        surahs = state.surahs,
                        onSurahClick = onSurahClick,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }

            is HomeUiState.Error -> {
                ErrorState(
                    message = state.message,
                    onRetry = { viewModel.loadSurahs() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}

@Composable
private fun SurahList(
    surahs: List<Surah>,
    onSurahClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimensions.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
    ) {
        items(
            items = surahs,
            key = { it.number }
        ) { surah ->
            SurahCard(
                surahNumber = surah.number,
                surahName = surah.name,
                surahNameArabic = surah.nameArabic,
                revelation = surah.revelationType.name,
                ayahCount = surah.numberOfAyahs,
                onClick = { onSurahClick(surah.number) }
            )
        }
    }
}
