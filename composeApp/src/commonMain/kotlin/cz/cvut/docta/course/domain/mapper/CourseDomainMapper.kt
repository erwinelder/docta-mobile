package cz.cvut.docta.course.domain.mapper

import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseWithProgress


fun CourseWithProgress.toCourse(): Course {
    return Course(code = code, locale = locale, name = name, sectionCount = sectionCount)
}