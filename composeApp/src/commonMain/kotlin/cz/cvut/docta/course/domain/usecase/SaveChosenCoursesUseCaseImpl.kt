package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.core.utils.excludeItems
import cz.cvut.docta.course.data.repository.ChosenCourseRepository
import cz.cvut.docta.course.mapper.toChosenCourseEntities

class SaveChosenCoursesUseCaseImpl(
    private val chosenCourseRepository: ChosenCourseRepository
) : SaveChosenCoursesUseCase {

    override suspend fun save(codes: List<String>) {
        val toUpsert = codes.toChosenCourseEntities()
        val toDelete = chosenCourseRepository.getAllChosenCourses().excludeItems(toUpsert) { it.code }

        chosenCourseRepository.deleteAndUpsertCourses(toDelete = toDelete, toUpsert = toUpsert)
    }

}