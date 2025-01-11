package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight

class GetAllCoursesUseCaseTemp : GetAllCoursesUseCase {
    override suspend fun execute(): List<CourseLightweight> {
        return listOf(
            CourseLightweight(
                code = "course_code_1",
                locale = "cs",
                name = "1. Course name"
            ),
            CourseLightweight(
                code = "course_code_2",
                locale = "en",
                name = "2. Course name"
            ),
        )
    }
}