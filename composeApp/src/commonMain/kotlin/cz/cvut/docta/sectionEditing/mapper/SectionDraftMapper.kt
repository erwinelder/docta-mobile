package cz.cvut.docta.sectionEditing.mapper

import cz.cvut.docta.section.data.model.SectionDto
import cz.cvut.docta.sectionEditing.data.model.SectionDraftEntity
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft


fun List<SectionDraftEntity>.toDomainModels(): List<SectionDraft> {
    return map { it.toDomain() }
}

fun SectionDraftEntity.toDomain(): SectionDraft {
    return SectionDraft(
        courseCode = courseCode,
        id = id,
        name = name
    )
}


fun SectionDraft.toDataModel(): SectionDraftEntity {
    return SectionDraftEntity(
        courseCode = courseCode,
        id = id,
        name = name
    )
}


fun SectionDto.toSectionDraft(): SectionDraft {
    return SectionDraft(
        courseCode = courseCode,
        id = id,
        name = name
    )
}
