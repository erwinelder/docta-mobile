package cz.cvut.docta.lesson.domain.usecase

import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty

class GetSectionLessonsUseCaseTemp : GetSectionLessonsUseCase {
    override suspend fun execute(sectionId: Long): List<Lesson> {
        return listOf(
            Lesson.OneStepQuestionsLesson(
                id = 1,
                name = "Practice theory",
                isDone = true,
                difficulty = LessonDifficulty.Easy
            ),
            Lesson.OneStepQuestionsLesson(
                id = 2,
                name = "Practice limits vol.1",
                isDone = false,
                difficulty = LessonDifficulty.Medium
            ),
            Lesson.StepByStepLesson(
                id = 3,
                name = "(x→2) lim x² = 4",
                isDone = true,
                difficulty = LessonDifficulty.Medium,
                description = "Solve the limit by definition"
            ),
            Lesson.OneStepQuestionsLesson(
                id = 4,
                name = "Practice limits vol.2",
                isDone = false,
                difficulty = LessonDifficulty.Medium
            ),
            Lesson.StepByStepLesson(
                id = 5,
                name = "(x→∞) lim (3x³−2x²−1)",
                isDone = false,
                difficulty = LessonDifficulty.Medium,
                description = "Solve the limit"
            ),
            Lesson.TestLesson(
                id = 6,
                name = "Review limits",
                isDone = false
            ),
        )
    }
}