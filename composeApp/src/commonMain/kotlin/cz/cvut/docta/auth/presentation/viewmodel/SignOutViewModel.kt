package cz.cvut.docta.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.docta.auth.domain.usecase.SignOutUseCase

class SignOutViewModel(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    suspend fun signOut() {
        signOutUseCase.execute()
    }

}