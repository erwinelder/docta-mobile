package cz.cvut.docta.auth.presentation.model

sealed class UserNameEditingState {

    data class Idle(val name: String) : UserNameEditingState()

    data object Saving : UserNameEditingState()

    data object Editing : UserNameEditingState()

}