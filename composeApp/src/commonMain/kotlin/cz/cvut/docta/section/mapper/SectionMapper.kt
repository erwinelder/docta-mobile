package cz.cvut.docta.section.mapper

import cz.cvut.docta.section.data.model.SectionDto
import cz.cvut.docta.section.data.model.SectionWithProgressDto
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.domain.model.SectionWithProgress


fun List<SectionDto>.toDomainModels(): List<Section> {
    return map { it.toDomainModel() }
}

fun SectionDto.toDomainModel(): Section {
    return Section(
        courseCode = courseCode,
        id = id,
        orderNum = orderNum,
        name = name,
        lessonCount = lessonCount
    )
}


fun SectionWithProgressDto.toDomainModel(): SectionWithProgress {
    return when (completedCount) {
        0 -> SectionWithProgress.NotStarted(
            courseCode = courseCode,
            id = id,
            orderNum = orderNum,
            name = name,
            lessonCount = lessonCount
        )
        lessonCount -> SectionWithProgress.Completed(
            courseCode = courseCode,
            id = id,
            orderNum = orderNum,
            name = name,
            lessonCount = lessonCount
        )
        else -> SectionWithProgress.InProgress(
            courseCode = courseCode,
            id = id,
            orderNum = orderNum,
            name = name,
            lessonCount = lessonCount,
            completed = completedCount
        )
    }
}
