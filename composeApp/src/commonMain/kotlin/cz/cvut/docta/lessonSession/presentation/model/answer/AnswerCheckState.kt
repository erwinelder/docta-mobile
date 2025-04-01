package cz.cvut.docta.lessonSession.presentation.model.answer

import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult

sealed class AnswerCheckState<T : AnswerCheckResult> {

    class Idle<T : AnswerCheckResult> : AnswerCheckState<T>()

    class Loading<T : AnswerCheckResult> : AnswerCheckState<T>()

    data class Result<T : AnswerCheckResult>(val result: T) : AnswerCheckState<T>()


    fun getResultOrNull(): T? {
        return when (this) {
            is Result -> this.result
            else -> null
        }
    }

}