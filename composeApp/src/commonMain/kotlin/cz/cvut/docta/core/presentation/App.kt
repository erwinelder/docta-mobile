package cz.cvut.docta.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.core.presentation.component.other.AppBackground
import cz.cvut.docta.core.presentation.theme.DoctaTheme
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.docta_icon
import org.jetbrains.compose.resources.painterResource
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
                    visible = isSetUp == null,
                    exit = fadeOut(
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.docta_icon),
                            contentDescription = "Docta icon",
                            modifier = Modifier.size(128.dp)
                        )
                    }
                }
                AnimatedVisibility(
                    visible = isSetUp != null,
                    enter = fadeIn(
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    )
                ) {
                    MainApplicationContent()
                }
            }
        }
    }
}