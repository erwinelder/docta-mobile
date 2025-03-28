package cz.cvut.docta.auth.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AuthScreens {

    @Serializable
    data class SignIn(val email: String = "") : AuthScreens

    @Serializable
    data class SignUp(val email: String = "") : AuthScreens

    @Serializable
    data object EmailVerification : AuthScreens

    @Serializable
    data class Profile(val userId: Int = 0) : AuthScreens

    @Serializable
    data object SignOut : AuthScreens

    @Serializable
    data object DeleteOwnAccount : AuthScreens

    @Serializable
    data class DeleteUserAccount(val userId: Int) : AuthScreens

}