package cz.cvut.docta.section.domain.model

data class SectionWithStatistics(
    val id: Long,
    val name: String,
    val statistics: SectionStatistics
)
