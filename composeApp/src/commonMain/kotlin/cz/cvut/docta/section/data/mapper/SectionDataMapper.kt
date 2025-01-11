package cz.cvut.docta.section.data.mapper

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity
import cz.cvut.docta.section.data.local.model.SectionEntity


fun List<SectionRemoteEntity>.toSectionEntitiesToSync(): EntitiesToSynchronise<SectionEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = SectionRemoteEntity::toSectionEntity
    )
}

fun SectionRemoteEntity.toSectionEntity(): SectionEntity {
    return SectionEntity(
        courseCode = courseCode,
        id = id,
        name = name
    )
}