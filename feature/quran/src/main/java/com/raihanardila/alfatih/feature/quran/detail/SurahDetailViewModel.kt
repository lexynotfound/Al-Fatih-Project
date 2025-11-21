package com.raihanardila.alfatih.feature.quran.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.domain.usecase.GetAyahsBySurahUseCase
import com.raihanardila.alfatih.domain.usecase.GetSurahByNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahDetailViewModel @Inject constructor(
    private val getSurahByNumberUseCase: GetSurahByNumberUseCase,
    private val getAyahsBySurahUseCase: GetAyahsBySurahUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val surahNumber: Int = savedStateHandle.get<String>("surahNumber")?.toIntOrNull() ?: 1

    private val _uiState = MutableStateFlow(SurahDetailUiState())
    val uiState: StateFlow<SurahDetailUiState> = _uiState.asStateFlow()

    init {
        loadSurahDetails()
    }

    private fun loadSurahDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            // Load Surah info
            getSurahByNumberUseCase(surahNumber).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { it.copy(surah = result.data) }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.exception.message ?: "Failed to load surah"
                            )
                        }
                    }
                    is Result.Loading -> {
                        // Keep loading state
                    }
                }
            }
        }

        viewModelScope.launch {
            // Load Ayahs
            getAyahsBySurahUseCase(surahNumber).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                ayahs = result.data
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.exception.message ?: "Failed to load ayahs"
                            )
                        }
                    }
                    is Result.Loading -> {
                        // Keep loading state
                    }
                }
            }
        }
    }

    fun retry() {
        loadSurahDetails()
    }
}
