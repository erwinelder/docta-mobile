package cz.cvut.docta.core.presentation.theme

import androidx.compose.runtime.Composable
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.currentTraitCollection

@Composable
actual fun isSystemInDarkTheme(): Boolean = UIUserInterfaceStyle.UIUserInterfaceStyleDark ==
        UITraitCollection.currentTraitCollection.userInterfaceStyle
