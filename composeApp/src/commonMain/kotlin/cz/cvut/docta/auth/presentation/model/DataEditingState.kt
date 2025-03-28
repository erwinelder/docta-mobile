package cz.cvut.docta.auth.presentation.model

sealed class DataEditingState {

    data object Idle : DataEditingState()

    data object Editing : DataEditingState()

    data object Saving : DataEditingState()


    fun toggle(onResetData: (() -> Unit)?): DataEditingState {
        return when (this) {
            is Idle -> Editing
            is Editing -> {
                onResetData?.invoke()
                Idle
            }
            is Saving -> this
        }
    }

}