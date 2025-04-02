package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import cz.cvut.docta.errorHandling.presentation.model.ResultWithButtonState
import dev.icerock.moko.resources.StringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.short_arrow_left_icon
import org.jetbrains.compose.resources.DrawableResource


fun LessonSessionError.toResultState(): ResultState {
    return ResultState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}

fun LessonSessionError.toResultWithButtonState(): ResultWithButtonState {
    return ResultWithButtonState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes(),
        buttonTextRes = this.asButtonTextRes(),
        buttonIconRes = this.asButtonIconRes()
    )
}

private fun LessonSessionError.asTitleRes(): StringResource {
    return when (this) {
        LessonSessionError.LessonSessionIsEmpty -> SharedRes.strings.empty_lesson_session
        LessonSessionError.AnswerCheckError -> SharedRes.strings.oops
        LessonSessionError.LessonSessionFinishingError -> SharedRes.strings.oops
        LessonSessionError.LessonSessionDeletionError -> SharedRes.strings.oops
    }
}

private fun LessonSessionError.asMessageRes(): StringResource? {
    return when (this) {
        LessonSessionError.LessonSessionIsEmpty -> SharedRes.strings.empty_lesson_session_error
        LessonSessionError.AnswerCheckError -> SharedRes.strings.answer_check_error
        LessonSessionError.LessonSessionFinishingError -> SharedRes.strings.lesson_session_finishing_error
        LessonSessionError.LessonSessionDeletionError -> SharedRes.strings.lesson_session_deleting_error
    }
}

private fun LessonSessionError.asButtonTextRes(): StringResource {
    return when (this) {
        LessonSessionError.LessonSessionIsEmpty -> SharedRes.strings.back
        LessonSessionError.AnswerCheckError -> SharedRes.strings.close
        LessonSessionError.LessonSessionFinishingError -> SharedRes.strings.close
        LessonSessionError.LessonSessionDeletionError -> SharedRes.strings.close
    }
}

private fun LessonSessionError.asButtonIconRes(): DrawableResource? {
    return when (this) {
        LessonSessionError.LessonSessionIsEmpty -> Res.drawable.short_arrow_left_icon
        LessonSessionError.AnswerCheckError -> Res.drawable.close_icon
        LessonSessionError.LessonSessionFinishingError -> Res.drawable.close_icon
        LessonSessionError.LessonSessionDeletionError -> Res.drawable.close_icon
    }
}
