package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.repository.ChosenCourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetChosenCoursesUseCaseImpl(
    private val chosenCourseRepository: ChosenCourseRepository
) : GetChosenCoursesUseCase {

    override suspend fun getFlow(): Flow<List<String>> {
        return chosenCourseRepository.getAllChosenCoursesFlow().map { entities ->
            entities.map { it.code }
        }
    }

}