package cz.cvut.docta.section.mapper

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.domain.model.Section


fun List<SectionEntity>.toSectionsLightweight(): List<Section> {
    return map { it.toSectionLightweight() }
}

fun SectionEntity.toSectionLightweight(): Section {
    return Section(
        id = id,
        name = name
    )
}