package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseLightweight
import kotlinx.coroutines.flow.Flow

interface GetAllCoursesUseCase {
    suspend fun execute(): Flow<List<CourseLightweight>>
}