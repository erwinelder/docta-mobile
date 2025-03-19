package cz.cvut.docta.section.mapper

import cz.cvut.docta.section.data.model.SectionDto
import cz.cvut.docta.section.domain.model.Section


fun List<SectionDto>.toDomainModels(): List<Section> {
    return map { it.toDomainModel() }
}

fun SectionDto.toDomainModel(): Section {
    return Section(
        id = id,
        name = name
    )
}
