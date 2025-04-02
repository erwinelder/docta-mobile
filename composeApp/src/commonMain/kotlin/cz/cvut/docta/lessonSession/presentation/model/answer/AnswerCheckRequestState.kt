package cz.cvut.docta.lessonSession.presentation.model.answer

import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.presentation.model.ResultState
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import dev.icerock.moko.resources.StringResource

sealed class AnswerCheckRequestState<T : AnswerCheckResult> {

    data class Default<T : AnswerCheckResult>(
        val state: AnswerCheckState<T>
    ) : AnswerCheckRequestState<T>()

    data class Error<T : AnswerCheckResult>(val error: ResultState) : AnswerCheckRequestState<T>()


    fun getButtonStringRes(): StringResource {
        return when (this) {
            is Default -> when (this.state) {
                is AnswerCheckState.Idle -> SharedRes.strings.check
                is AnswerCheckState.Loading -> SharedRes.strings.checking_loader
                is AnswerCheckState.Result -> SharedRes.strings.continue_
            }
            is Error -> SharedRes.strings.continue_
        }
    }

    fun isNotLoading(): Boolean {
        return when (this) {
            is Default -> this.state !is AnswerCheckState.Loading
            is Error -> true
        }
    }

    fun shouldDisplayContinueButton(): Boolean {
        return when (this) {
            is Default -> this.state is AnswerCheckState.Result
            is Error -> true
        }
    }

    fun isCorrect(): Boolean? {
        return when (this) {
            is Default -> (this.state as? AnswerCheckState.Result)?.result?.isCorrect
            is Error -> null
        }
    }

}


