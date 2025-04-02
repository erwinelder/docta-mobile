package cz.cvut.docta.errorHandling.presentation.model

import dev.icerock.moko.resources.StringResource

sealed class RequestState {

    data class Loading(val messageRes: StringResource) : RequestState()

    data class Result(val resultState: ResultWithButtonState) : RequestState()

}