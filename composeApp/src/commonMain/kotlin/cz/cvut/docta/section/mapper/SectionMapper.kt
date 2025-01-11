package cz.cvut.docta.section.mapper

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.domain.model.SectionLightweight


fun List<SectionEntity>.toSectionsLightweight(): List<SectionLightweight> {
    return map { it.toSectionLightweight() }
}

fun SectionEntity.toSectionLightweight(): SectionLightweight {
    return SectionLightweight(
        id = id,
        name = name
    )
}