package cz.cvut.docta.core.domain.course

import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

data class CourseContentContainer(
    val course: Course,
    val sectionsWithLessons: List<CourseSectionWithLessons>,
    val questionsWithAnswers: List<QuestionWithAnswers>
)
