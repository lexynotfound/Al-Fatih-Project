package com.raihanardila.alfatih.domain.usecase

import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAyahsUseCase @Inject constructor(
    private val quranRepository: QuranRepository
) {
    operator fun invoke(query: String): Flow<Result<List<Ayah>>> {
        return quranRepository.searchAyahs(query)
    }
}
