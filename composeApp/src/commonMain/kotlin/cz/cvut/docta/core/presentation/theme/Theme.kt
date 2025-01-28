package cz.cvut.docta.core.presentation.theme

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalRippleConfiguration
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.domain.app.WindowType


val LocalAppTheme = compositionLocalOf { AppTheme.Light }
val LocalColors = compositionLocalOf<DoctaPalette> { DoctaPalette.Light }
val LocalTypography = compositionLocalOf { DoctaTypography() }
val LocalWindowType = compositionLocalOf { WindowType.Compact }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoctaTheme(
    boxWithConstraintsScope: BoxWithConstraintsScope,
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appTheme = if (isSystemInDarkTheme) AppTheme.Dark else AppTheme.Light
    val doctaColors = if (isSystemInDarkTheme) DoctaPalette.Dark else DoctaPalette.Light
    val typography = DoctaTypography()
    val windowType = when {
        boxWithConstraintsScope.maxWidth < 600.dp -> WindowType.Compact
        boxWithConstraintsScope.maxWidth < 840.dp -> WindowType.Medium
        else -> WindowType.Expanded
    }

    CompositionLocalProvider(
        LocalAppTheme provides appTheme,
        LocalColors provides doctaColors,
        LocalTypography provides typography,
        LocalWindowType provides windowType,
        LocalRippleConfiguration provides null
    ) {
        MaterialTheme(content = content)
    }
}


val CurrAppTheme: AppTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAppTheme.current

val DoctaColors: DoctaPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

val CurrWindowType: WindowType
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current

val WindowTypeIsCompact: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Compact

val WindowTypeIsMedium: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Medium

val WindowTypeIsExpanded: Boolean
    @Composable
    @ReadOnlyComposable
    get() = LocalWindowType.current == WindowType.Expanded