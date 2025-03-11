package cz.cvut.docta.course.domain.usecase

interface SaveChosenCoursesUseCase {
    suspend fun save(codes: List<String>)
}