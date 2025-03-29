package cz.cvut.docta.lesson.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface LessonSessionScreens {

    @Serializable
    data object LessonStarter: LessonSessionScreens

    @Serializable
    data object OpenAnswerQuestion : LessonSessionScreens

    @Serializable
    data object FillInBlanksQuestion : LessonSessionScreens

    @Serializable
    data object AnswerOptionsQuestion : LessonSessionScreens

    @Serializable
    data object CategorizationQuestion : LessonSessionScreens

    @Serializable
    data object QuestionAnswerPairsQuestion : LessonSessionScreens

    @Serializable
    data object StepByStepQuestion : LessonSessionScreens

    @Serializable
    data object LessonResults : LessonSessionScreens

}