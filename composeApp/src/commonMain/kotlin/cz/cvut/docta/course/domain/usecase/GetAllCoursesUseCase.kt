package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight
import kotlinx.coroutines.flow.Flow

interface GetAllLightweightCoursesUseCase {
    fun execute(): Flow<List<CourseLightweight>>
}