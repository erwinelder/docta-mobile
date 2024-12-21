package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.components.other.AppBackground
import cz.cvut.docta.core.presentation.theme.DoctaTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this
        ) {
            Box {
                AppBackground()
                MainApplicationContent()
            }
        }
    }
}