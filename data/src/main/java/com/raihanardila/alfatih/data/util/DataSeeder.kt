package com.raihanardila.alfatih.data.util

import com.raihanardila.alfatih.data.local.dao.AyahDao
import com.raihanardila.alfatih.data.local.dao.SurahDao
import com.raihanardila.alfatih.data.local.entity.AyahEntity
import com.raihanardila.alfatih.data.local.entity.SurahEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSeeder @Inject constructor(
    private val surahDao: SurahDao,
    private val ayahDao: AyahDao
) {
    suspend fun seedDatabase() = withContext(Dispatchers.IO) {
        // Sample Surahs
        val surahs = listOf(
            SurahEntity(
                number = 1,
                name = "Al-Fatihah",
                nameArabic = "الفاتحة",
                nameEnglish = "The Opening",
                numberOfAyahs = 7,
                revelationType = "MECCAN",
                revelationPlace = "Mecca"
            ),
            SurahEntity(
                number = 2,
                name = "Al-Baqarah",
                nameArabic = "البقرة",
                nameEnglish = "The Cow",
                numberOfAyahs = 286,
                revelationType = "MEDINAN",
                revelationPlace = "Medina"
            ),
            SurahEntity(
                number = 3,
                name = "Ali 'Imran",
                nameArabic = "آل عمران",
                nameEnglish = "Family of Imran",
                numberOfAyahs = 200,
                revelationType = "MEDINAN",
                revelationPlace = "Medina"
            ),
            SurahEntity(
                number = 112,
                name = "Al-Ikhlas",
                nameArabic = "الإخلاص",
                nameEnglish = "The Sincerity",
                numberOfAyahs = 4,
                revelationType = "MECCAN",
                revelationPlace = "Mecca"
            ),
            SurahEntity(
                number = 113,
                name = "Al-Falaq",
                nameArabic = "الفلق",
                nameEnglish = "The Daybreak",
                numberOfAyahs = 5,
                revelationType = "MECCAN",
                revelationPlace = "Mecca"
            ),
            SurahEntity(
                number = 114,
                name = "An-Nas",
                nameArabic = "الناس",
                nameEnglish = "Mankind",
                numberOfAyahs = 6,
                revelationType = "MECCAN",
                revelationPlace = "Mecca"
            )
        )

        surahDao.insertSurahs(surahs)

        // Sample Ayahs for Al-Fatihah
        val ayahsAlFatihah = listOf(
            AyahEntity(
                number = 1,
                numberInSurah = 1,
                text = "In the name of Allah, the Entirely Merciful, the Especially Merciful.",
                textArabic = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 2,
                numberInSurah = 2,
                text = "All praise is due to Allah, Lord of the worlds.",
                textArabic = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 3,
                numberInSurah = 3,
                text = "The Entirely Merciful, the Especially Merciful,",
                textArabic = "الرَّحْمَٰنِ الرَّحِيمِ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 4,
                numberInSurah = 4,
                text = "Sovereign of the Day of Recompense.",
                textArabic = "مَالِكِ يَوْمِ الدِّينِ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 5,
                numberInSurah = 5,
                text = "It is You we worship and You we ask for help.",
                textArabic = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 6,
                numberInSurah = 6,
                text = "Guide us to the straight path.",
                textArabic = "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
                surahNumber = 1,
                juz = 1,
                page = 1
            ),
            AyahEntity(
                number = 7,
                numberInSurah = 7,
                text = "The path of those upon whom You have bestowed favor, not of those who have evoked Your anger or of those who are astray.",
                textArabic = "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
                surahNumber = 1,
                juz = 1,
                page = 1
            )
        )

        ayahDao.insertAyahs(ayahsAlFatihah)

        // Sample Ayahs for Al-Ikhlas
        val ayahsAlIkhlas = listOf(
            AyahEntity(
                number = 6221,
                numberInSurah = 1,
                text = "Say, He is Allah, the One.",
                textArabic = "قُلْ هُوَ اللَّهُ أَحَدٌ",
                surahNumber = 112,
                juz = 30,
                page = 604
            ),
            AyahEntity(
                number = 6222,
                numberInSurah = 2,
                text = "Allah, the Eternal Refuge.",
                textArabic = "اللَّهُ الصَّمَدُ",
                surahNumber = 112,
                juz = 30,
                page = 604
            ),
            AyahEntity(
                number = 6223,
                numberInSurah = 3,
                text = "He neither begets nor is born,",
                textArabic = "لَمْ يَلِدْ وَلَمْ يُولَدْ",
                surahNumber = 112,
                juz = 30,
                page = 604
            ),
            AyahEntity(
                number = 6224,
                numberInSurah = 4,
                text = "Nor is there to Him any equivalent.",
                textArabic = "وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ",
                surahNumber = 112,
                juz = 30,
                page = 604
            )
        )

        ayahDao.insertAyahs(ayahsAlIkhlas)
    }
}
