package cz.cvut.docta.section_draft.mapper

import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section_draft.data.model.SectionDraftEntity
import cz.cvut.docta.section_draft.domain.model.SectionDraft

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