package cz.cvut.docta.course.domain.usecase

interface AddCourseToChosenUseCase {
    suspend fun add(code: String)
}