package com.raihanardila.alfatih.data.mapper

import com.raihanardila.alfatih.data.local.entity.AyahEntity
import com.raihanardila.alfatih.data.local.entity.SurahEntity
import com.raihanardila.alfatih.domain.model.Ayah
import com.raihanardila.alfatih.domain.model.RevelationType
import com.raihanardila.alfatih.domain.model.Surah

fun SurahEntity.toDomain(): Surah {
    return Surah(
        number = number,
        name = name,
        nameArabic = nameArabic,
        nameEnglish = nameEnglish,
        numberOfAyahs = numberOfAyahs,
        revelationType = if (revelationType == "MECCAN") RevelationType.MECCAN else RevelationType.MEDINAN,
        revelationPlace = revelationPlace
    )
}

fun Surah.toEntity(): SurahEntity {
    return SurahEntity(
        number = number,
        name = name,
        nameArabic = nameArabic,
        nameEnglish = nameEnglish,
        numberOfAyahs = numberOfAyahs,
        revelationType = revelationType.name,
        revelationPlace = revelationPlace
    )
}

fun AyahEntity.toDomain(): Ayah {
    return Ayah(
        number = number,
        numberInSurah = numberInSurah,
        text = text,
        textArabic = textArabic,
        surahNumber = surahNumber,
        juz = juz,
        page = page
    )
}

fun Ayah.toEntity(): AyahEntity {
    return AyahEntity(
        number = number,
        numberInSurah = numberInSurah,
        text = text,
        textArabic = textArabic,
        surahNumber = surahNumber,
        juz = juz,
        page = page
    )
}
