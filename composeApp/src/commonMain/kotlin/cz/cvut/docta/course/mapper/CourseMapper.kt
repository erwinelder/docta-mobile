package cz.cvut.docta.course.mapper

import cz.cvut.docta.course.data.model.CourseDto
import cz.cvut.docta.course.data.model.CourseLocaleDto
import cz.cvut.docta.course.data.model.CourseWithProgressDto
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.domain.model.CourseWithProgress


fun CourseLocaleDto.toDomainModel(): CourseLocale {
    return when (this) {
        CourseLocaleDto.English -> CourseLocale.English
        CourseLocaleDto.Czech -> CourseLocale.Czech
    }
}


fun CourseDto.toDomainModel(): Course {
    return Course(
        code = code,
        locale = locale.toDomainModel(),
        name = name,
        sectionCount = sectionCount
    )
}


fun CourseWithProgressDto.toDomainModel(): CourseWithProgress {
    return when (completedCount) {
        0 -> CourseWithProgress.NotStarted(
            code = code,
            locale = locale.toDomainModel(),
            name = name,
            sectionCount = sectionCount
        )
        sectionCount -> CourseWithProgress.Completed(
            code = code,
            locale = locale.toDomainModel(),
            name = name,
            sectionCount = sectionCount
        )
        else -> CourseWithProgress.InProgress(
            code = code,
            locale = locale.toDomainModel(),
            name = name,
            sectionCount = sectionCount,
            completed = completedCount
        )
    }
}
