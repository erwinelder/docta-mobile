package cz.cvut.docta.sectionEditing.mapper

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.mapper.toDomainModel
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft

fun SectionDraftEntity.toDomain(): SectionDraft {
    return SectionDraft(
        courseCode = courseCode,
        id = id,
        name = name,
    )
}

fun SectionDraft.toEntity(): SectionDraftEntity {
    return SectionDraftEntity(
        courseCode = courseCode,
        id = id,
        name = name,
    )
}

fun SectionEntity.toSectionDraft(): SectionDraft {
    return SectionDraft(
        courseCode = courseCode,
        id = id,
        name = name,
    )
}

fun List<SectionDraftEntity>.toSectionDrafts(): List<SectionDraft> {
    return map { it.toDomain() }
}