package cz.cvut.docta.lesson.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface LessonScreens {

    @Serializable
    data class LessonStarter(val lessonId: Long): LessonScreens

    @Serializable
    data object OpenAnswerQuestion : LessonScreens

    @Serializable
    data object FillInBlanksQuestion : LessonScreens

    @Serializable
    data object AnswerOptionsQuestion : LessonScreens

    @Serializable
    data object QuestionAnswerPairsQuestion : LessonScreens

    @Serializable
    data object StepByStepQuestion : LessonScreens

    @Serializable
    data object LessonResults : LessonScreens

}