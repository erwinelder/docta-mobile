package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetAllCoursesUseCaseTemp : GetAllCoursesUseCase {
    override fun execute(): Flow<List<CourseLightweight>> {
        return flowOf(
            listOf(
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
        )
    }
}