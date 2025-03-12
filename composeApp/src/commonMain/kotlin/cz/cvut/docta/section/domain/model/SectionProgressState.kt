package cz.cvut.docta.section.domain.model

sealed class SectionProgressState(
    open val id: Long,
    open val name: String,
) {
    data class Done(
        override val id: Long,
        override val name: String,
    ) : SectionProgressState(id, name)

    data class InProgress(
        override val id: Long,
        override val name: String,
        val statistics: SectionStatistics
    ) : SectionProgressState(id, name)

    data class Locked(
        override val id: Long,
        override val name: String
    ) : SectionProgressState(id, name)
}