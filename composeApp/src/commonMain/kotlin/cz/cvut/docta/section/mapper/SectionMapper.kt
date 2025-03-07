package cz.cvut.docta.section.mapper

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.domain.model.Section


fun List<SectionEntity>.toDomainModels(): List<Section> {
    return map { it.toDomainModel() }
}

fun SectionEntity.toDomainModel(): Section {
    return Section(
        id = id,
        name = name
    )
}