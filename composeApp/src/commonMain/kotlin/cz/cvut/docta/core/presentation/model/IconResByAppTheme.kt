package cz.cvut.docta.core.presentation.model

import cz.cvut.docta.core.domain.app.AppTheme
import org.jetbrains.compose.resources.DrawableResource

data class IconResByAppTheme(
    val light: DrawableResource,
    val dark: DrawableResource
) {

    fun get(appTheme: AppTheme): DrawableResource {
        return when (appTheme) {
            AppTheme.Light -> light
            AppTheme.Dark -> dark
        }
    }

}
