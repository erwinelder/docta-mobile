package cz.cvut.docta.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.presentation.component.other.AppBackground
import cz.cvut.docta.core.presentation.theme.DoctaTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    var isSetUp by remember { mutableStateOf<Boolean?>(null) }

    val userContext = koinInject<UserContext>()
    LaunchedEffect(true) {
        isSetUp = userContext.checkAuthTokenValidity()
    }

    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this
        ) {
            Box {
                AppBackground()
                AnimatedVisibility(
                    visible = isSetUp != null,
                    enter = fadeIn()
                ) {
                    MainApplicationContent()
                }
            }
        }
    }
}