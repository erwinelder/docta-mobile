package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseLocale

class GetAllCoursesUseCaseTemp : GetAllCoursesUseCase {
    override suspend fun execute(): List<Course> {
        return listOf(
            Course(
                code = "course_code_1",
                locale = CourseLocale.Czech,
                name = "1. Course name"
            ),
            Course(
                code = "course_code_2",
                locale = CourseLocale.English,
                name = "2. Course name"
            ),
        )
    }
}