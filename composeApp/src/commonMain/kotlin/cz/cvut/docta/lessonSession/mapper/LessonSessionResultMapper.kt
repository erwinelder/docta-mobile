package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import dev.icerock.moko.resources.StringResource


fun LessonSessionError.toResultState(): ResultState {
    return ResultState(
        isSuccessful = false,
        titleRes = this.asTitleRes(),
        messageRes = this.asMessageRes()
    )
}

private fun LessonSessionError.asTitleRes(): StringResource {
    return when (this) {
        LessonSessionError.AnswerCheckError -> SharedRes.strings.oops
    }
}

private fun LessonSessionError.asMessageRes(): StringResource? {
    return when (this) {
        LessonSessionError.AnswerCheckError -> SharedRes.strings.answer_check_error
    }
}
