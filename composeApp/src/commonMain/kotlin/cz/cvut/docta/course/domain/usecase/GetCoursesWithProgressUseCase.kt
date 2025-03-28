package cz.cvut.docta.course.domain.usecase

import cz.cvut.docta.course.domain.model.CourseWithProgress

interface GetCoursesWithProgressUseCase {
    suspend fun execute(codes: List<String>): List<CourseWithProgress>
}