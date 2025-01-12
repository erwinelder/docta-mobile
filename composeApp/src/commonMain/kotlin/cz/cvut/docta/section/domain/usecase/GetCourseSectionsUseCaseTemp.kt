package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.SectionWithStatistics
import cz.cvut.docta.section.domain.model.SectionStatistics

class GetCourseSectionsUseCaseTemp : GetCourseSectionsUseCase {
    override suspend fun execute(courseCode: String): List<SectionWithStatistics> {
        return listOf(
            SectionWithStatistics(
                id = 1,
                name = "Úvod do matematické analýzy, základní principy kalkulu",
                statistics = SectionStatistics()
            ),
            SectionWithStatistics(
                id = 2,
                name = "Reálná čísla, základní matematická terminologie",
                statistics = SectionStatistics()
            ),
            SectionWithStatistics(
                id = 3,
                name = "Limita funkce, spojitost.",
                statistics = SectionStatistics()
            ),
            SectionWithStatistics(
                id = 4,
                name = "Derivace funkce, její vlastnosti a význam",
                statistics = SectionStatistics()
            ),
            SectionWithStatistics(
                id = 5,
                name = "L'Hospitalovo pravidlo, Taylorův polynom",
                statistics = SectionStatistics()
            ),
        )
    }
}