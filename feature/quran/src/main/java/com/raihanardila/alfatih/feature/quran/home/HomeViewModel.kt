package com.raihanardila.alfatih.feature.quran.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.domain.usecase.GetAllSurahsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllSurahsUseCase: GetAllSurahsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadSurahs()
    }

    fun loadSurahs() {
        viewModelScope.launch {
            getAllSurahsUseCase().collect { result ->
                _uiState.value = when (result) {
                    is Result.Loading -> HomeUiState.Loading
                    is Result.Success -> HomeUiState.Success(result.data)
                    is Result.Error -> HomeUiState.Error(
                        result.exception.message ?: "An error occurred"
                    )
                }
            }
        }
    }
}
