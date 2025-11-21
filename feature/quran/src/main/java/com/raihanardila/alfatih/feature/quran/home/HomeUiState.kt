package com.raihanardila.alfatih.feature.quran.home

import com.raihanardila.alfatih.domain.model.Surah

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val surahs: List<Surah>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
