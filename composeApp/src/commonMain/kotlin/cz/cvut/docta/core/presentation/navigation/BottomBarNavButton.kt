package cz.cvut.docta.core.presentation.navigation

import cz.cvut.docta.core.presentation.model.IconResByAppTheme
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.home_dark_active
import docta.composeapp.generated.resources.home_dark_inactive
import docta.composeapp.generated.resources.home_light_active
import docta.composeapp.generated.resources.home_light_inactive
import docta.composeapp.generated.resources.profile_dark_active
import docta.composeapp.generated.resources.profile_dark_inactive
import docta.composeapp.generated.resources.profile_light_active
import docta.composeapp.generated.resources.profile_light_inactive

sealed class BottomBarNavButton(
    val screen: MainScreens,
    val inactiveIconRes: IconResByAppTheme,
    val activeIconRes: IconResByAppTheme,
) {

    data object Home : BottomBarNavButton(
        screen = MainScreens.CoursesGraph,
        inactiveIconRes = IconResByAppTheme(
            light = Res.drawable.home_light_inactive,
            dark = Res.drawable.home_dark_inactive
        ),
        activeIconRes = IconResByAppTheme(
            light = Res.drawable.home_light_active,
            dark = Res.drawable.home_dark_active
        )
    )

    data object Profile : BottomBarNavButton(
        screen = MainScreens.ProfileGraph,
        inactiveIconRes = IconResByAppTheme(
            light = Res.drawable.profile_light_inactive,
            dark = Res.drawable.profile_dark_inactive
        ),
        activeIconRes = IconResByAppTheme(
            light = Res.drawable.profile_light_active,
            dark = Res.drawable.profile_dark_active
        )
    )


    fun getIconRes(isActive: Boolean): IconResByAppTheme {
        return if (isActive) activeIconRes else inactiveIconRes
    }


    companion object {

        fun asDefaultList(): List<BottomBarNavButton> {
            return listOf(
                Home,
                Profile
            )
        }

    }

}