package cz.cvut.docta.profile.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface ProfileScreens {

    @Serializable
    data object Profile : ProfileScreens

}