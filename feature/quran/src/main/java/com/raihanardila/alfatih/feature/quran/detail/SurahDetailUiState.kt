package com.raihanardila.alfatih.feature.quran.detail

import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.model.Surah

data class SurahDetailUiState(
    val isLoading: Boolean = true,
    val surah: Surah? = null,
    val ayahs: List<Ayah> = emptyList(),
    val error: String? = null
)
