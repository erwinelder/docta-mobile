package cz.cvut.docta.core.presentation.component.container

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.navigation.BottomBarNavButton
import cz.cvut.docta.core.presentation.theme.CurrAppTheme
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.utils.add
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavBar(
    isVisible: Boolean,
    padding: PaddingValues = PaddingValues(0.dp),
    anyScreenInHierarchyIsScreenProvider: (Any) -> Boolean,
    currentScreenIsScreenProvider: (Any) -> Boolean,
    onNavigateToScreen: (Any) -> Unit,
    barButtons: List<BottomBarNavButton> = BottomBarNavButton.asDefaultList()
) {
    var timerIsUp by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    val onButtonClick = { screen: Any ->
        if (!currentScreenIsScreenProvider(screen) && timerIsUp) {
            coroutineScope.launch {
                timerIsUp = false
                delay(500)
                timerIsUp = true
            }

            onNavigateToScreen(screen)
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { (it * 1.5).toInt() },
        exit = slideOutVertically { (it * 1.5).toInt() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.linearGradient(
                    colors = DoctaColors.glassSurfaceGradient,
                    start = Offset(0f, 1400f),
                    end = Offset(600f, 0f)
                ))
                .padding(
                    PaddingValues(vertical = 16.dp, horizontal = 4.dp).add(padding)
                )
        ) {
            barButtons.forEachIndexed { index, button ->
                BottomBarButton(
                    button = button,
                    anyScreenInHierarchyIsScreenProvider = anyScreenInHierarchyIsScreenProvider,
                    onClick = onButtonClick
                )
            }
        }
    }

    AnimatedVisibility(
        visible = !isVisible
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        )
    }
}

@Composable
private fun BottomBarButton(
    button: BottomBarNavButton,
    anyScreenInHierarchyIsScreenProvider: (Any) -> Boolean,
    onClick: (Any) -> Unit
) {
    val isActive = anyScreenInHierarchyIsScreenProvider(button.screen)

    AnimatedContent(
        targetState = button.getIconRes(isActive),
        transitionSpec = { fadeIn() togetherWith fadeOut() }
    ) { iconResByTheme ->
        Image(
            painter = painterResource(iconResByTheme.get(CurrAppTheme)),
            contentDescription = "bottom bar ${button.screen} icon",
            modifier = Modifier
                .bounceClickEffect {
                    onClick(button.screen)
                }
                .size(36.dp)
        )
    }
}
