package cz.cvut.docta.auth.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AuthScreens {

    @Serializable
    data object Profile : AuthScreens

    @Serializable
    data object SignIn : AuthScreens

    @Serializable
    data object SignUp : AuthScreens

    @Serializable
    data object EmailVerification : AuthScreens

    @Serializable
    data class ResultSuccess(val screenType: String) : AuthScreens

    @Serializable
    data object DeleteAccount : AuthScreens

}