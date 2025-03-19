package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.data.model.ChosenCourseEntity
import cz.cvut.docta.course.data.repository.ChosenCourseRepository

class AddCourseToChosenUseCaseImpl(
    private val chosenCourseRepository: ChosenCourseRepository
) : AddCourseToChosenUseCase {

    override suspend fun add(code: String) {
        val orderNum = chosenCourseRepository.getAllChosenCourses()
            .maxOfOrNull { it.orderNum }?.plus(1)
            ?: 0
        val chosenCourse = ChosenCourseEntity(code = code, orderNum = orderNum)
        chosenCourseRepository.upsertCourse(course = chosenCourse)
    }

}