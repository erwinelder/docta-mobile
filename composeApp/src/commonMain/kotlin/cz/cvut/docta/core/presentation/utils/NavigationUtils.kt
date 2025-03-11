package cz.cvut.docta.core.presentation.utils

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import kotlin.reflect.KClass


fun NavBackStackEntry?.currentScreenIsAnyOf(vararg screens: Any): Boolean {
    this ?: return false
    return screens.any { this.currentScreenIs(it) }
}


fun NavDestination?.currentScreenIs(screen: Any): Boolean {
    return this?.fromRoute() == screen::class.simpleName()
}

fun NavBackStackEntry?.currentScreenIs(screen: Any): Boolean {
    val screenSimpleName = screen::class.simpleName()
    val fromRoute = this?.fromRoute()

    return fromRoute == screenSimpleName
}

fun NavBackStackEntry?.anyScreenInHierarchyIs(screen: Any): Boolean {
    this ?: return false
    return this.destination.hierarchy.any { it.currentScreenIs(screen) }
}


fun NavBackStackEntry?.fromRoute(): String {
    return this?.destination?.route
        ?.substringBefore('/')?.substringAfterLast('.')?.substringBefore("?") ?: ""
}

fun NavDestination?.fromRoute(): String {
    return this?.route?.substringBefore('/')?.substringAfterLast('.')?.substringBefore('?') ?: ""
}


fun KClass<out Any>.simpleName(): String? {
    return this.simpleName?.substringAfterLast('$')?.substringBefore("(")
}
