package cz.cvut.docta.course.data.mapper

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity


fun List<CourseRemoteEntity>.toCourseEntitiesToSync(): EntitiesToSynchronise<CourseEntity> {
    return EntitiesToSynchronise.fromEntities(
        entities = this,
        deletedPredicate = { it.deleted },
        mapper = CourseRemoteEntity::toCourseEntity
    )
}

fun CourseRemoteEntity.toCourseEntity(): CourseEntity {
    return CourseEntity(
        code = code,
        locale = locale,
        name = name
    )
}