package cz.cvut.docta.course.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetChosenCoursesUseCase {
    suspend fun getFlow(): Flow<List<String>>
}