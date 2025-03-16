package cz.cvut.docta.auth.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AuthScreens {

    @Serializable
    data object Profile : AuthScreens

    @Serializable
    data class SignIn(val email: String) : AuthScreens

    @Serializable
    data class SignUp(val email: String) : AuthScreens

    @Serializable
    data object EmailVerification : AuthScreens

    @Serializable
    data class ResultSuccess(val screenType: String) : AuthScreens

    @Serializable
    data object DeleteAccount : AuthScreens

}