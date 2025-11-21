package com.raihanardila.alfatih.feature.quran.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raihanardila.alfatih.core.design.theme.Dimensions
import com.raihanardila.alfatih.core.ui.components.AlFatihTopAppBar
import com.raihanardila.alfatih.core.ui.components.AyahCard
import com.raihanardila.alfatih.core.ui.components.ErrorState
import com.raihanardila.alfatih.core.ui.components.LoadingIndicator
import com.raihanardila.alfatih.core.ui.components.Spacer
import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.model.Surah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahDetailScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SurahDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AlFatihTopAppBar(
                title = uiState.surah?.name ?: "Loading...",
                onNavigationClick = onNavigateBack
            )
        }
    ) { paddingValues ->
        when {
            uiState.isLoading -> {
                LoadingIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            uiState.error != null -> {
                ErrorState(
                    message = uiState.error!!,
                    onRetry = { viewModel.retry() },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            else -> {
                uiState.surah?.let { surah ->
                    SurahDetailContent(
                        surah = surah,
                        ayahs = uiState.ayahs,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
private fun SurahDetailContent(
    surah: Surah,
    ayahs: List<Ayah>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(Dimensions.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingMedium)
    ) {
        // Surah Header
        item {
            SurahHeader(surah = surah)
        }

        // Ayahs
        items(
            items = ayahs,
            key = { it.number }
        ) { ayah ->
            AyahCard(
                ayahNumber = ayah.numberInSurah,
                arabicText = ayah.textArabic,
                translation = ayah.text
            )
        }
    }
}

@Composable
private fun SurahHeader(
    surah: Surah,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimensions.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spacingSmall)
    ) {
        // Arabic Name
        Text(
            text = surah.nameArabic,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(height = Dimensions.spacingSmall)

        // English Name
        Text(
            text = surah.nameEnglish,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(height = Dimensions.spacingExtraSmall)

        // Info
        Text(
            text = "${surah.revelationType.name} • ${surah.numberOfAyahs} Ayahs",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
