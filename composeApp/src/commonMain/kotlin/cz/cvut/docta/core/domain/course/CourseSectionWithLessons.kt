package cz.cvut.docta.core.domain.course

import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.section.domain.model.Section

data class CourseSectionWithLessons(
    val section: Section,
    val defaultLessons: List<Lesson>
)
