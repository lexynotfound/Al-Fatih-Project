package com.raihanardila.alfatih.domain.usecase

import com.raihanardila.alfatih.core.common.Result
import com.raihanardila.alfatih.domain.model.Surah
import com.raihanardila.alfatih.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSurahByNumberUseCase @Inject constructor(
    private val quranRepository: QuranRepository
) {
    operator fun invoke(number: Int): Flow<Result<Surah>> {
        return quranRepository.getSurahByNumber(number)
    }
}
