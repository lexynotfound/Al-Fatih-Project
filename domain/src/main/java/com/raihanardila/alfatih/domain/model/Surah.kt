package com.raihanardila.alfatih.domain.model

data class Surah(
    val number: Int,
    val name: String,
    val nameArabic: String,
    val nameEnglish: String,
    val numberOfAyahs: Int,
    val revelationType: RevelationType,
    val revelationPlace: String
)

enum class RevelationType {
    MECCAN,
    MEDINAN
}
