package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonStatistics

class GetSectionLessonsDraftsUseCaseTemp : GetSectionLessonsWithStatisticsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
        return listOf(
            Lesson.Default(
                id = 1,
                name = "Practice theory",
                statistics = LessonStatistics(isDone = true),
                difficulty = LessonDifficulty.Easy
            ),
            Lesson.Default(
                id = 2,
                name = "Practice limits vol.1",
                statistics = LessonStatistics(isDone = false),
                difficulty = LessonDifficulty.Medium
            ),
            Lesson.StepByStep(
                id = 3,
                name = "(x→2) lim x² = 4",
                statistics = LessonStatistics(isDone = true),
                difficulty = LessonDifficulty.Medium,
                description = "Solve the limit by definition"
            ),
            Lesson.Default(
                id = 4,
                name = "Practice limits vol.2",
                statistics = LessonStatistics(isDone = false),
                difficulty = LessonDifficulty.Medium
            ),
            Lesson.StepByStep(
                id = 5,
                name = "(x→∞) lim (3x³−2x²−1)",
                statistics = LessonStatistics(isDone = false),
                difficulty = LessonDifficulty.Medium,
                description = "Solve the limit"
            ),
            Lesson.Test(
                id = 6,
                name = "Review limits",
                statistics = LessonStatistics(isDone = false)
            ),
        )
    }
}