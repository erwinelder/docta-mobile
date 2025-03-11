package cz.cvut.docta.section.data.mapper

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity
import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.data.remote.model.SectionRemoteDTO


fun List<SectionRemoteEntity>.toSectionEntitiesToSync(): EntitiesToSynchronise<SectionEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = SectionRemoteEntity::remoteEntityToLocal
    )
}

fun SectionRemoteEntity.remoteEntityToLocal(): SectionEntity {
    return SectionEntity(
        courseCode = courseCode,
        id = id,
        orderNum = orderNum,
        name = name
    )
}


fun SectionRemoteDTO.remoteDtoToLocalEntity(): SectionEntity {
    return SectionEntity(
        courseCode = courseCode,
        id = id,
        orderNum = orderNum,
        name = name
    )
}