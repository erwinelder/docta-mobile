package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import cz.cvut.docta.core.presentation.theme.NotoSans
import cz.cvut.docta.core.presentation.utils.getImeBottomInset
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScreenContainerWithBackNavButtonTitleAndGlassSurface(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateBack: () -> Unit,
    backButtonText: String,
    backButtonIconRes: DrawableResource? = null,
    title: String,
    glassSurfaceContent: @Composable BoxScope.() -> Unit,
    fillGlassSurface: Boolean = false,
    glassSurfaceFilledWidths: FilledWidthByScreenType = FilledWidthByScreenType(compact = .86f),
    buttonUnderGlassSurface: @Composable (() -> Unit)? = null,
    bottomButton: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val imeBottomInset by getImeBottomInset()
    val bottomPadding by animateDpAsState(imeBottomInset.coerceAtLeast(24.dp))
    val keyboardInFocus = bottomPadding > 100.dp

    ScreenContainer(
        screenPadding = screenPadding,
        padding = PaddingValues(top = 8.dp, bottom = bottomPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.clickable { focusManager.clearFocus() }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = fillGlassSurface)
        ) {
            AnimatedVisibility(
                visible = !keyboardInFocus, enter = fadeIn(), exit = fadeOut()
            ) {
                GlassSurfaceTopBackNavButton(
                    text = backButtonText, iconRes = backButtonIconRes, onClick = onNavigateBack
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            AnimatedVisibility(
                visible = !keyboardInFocus, enter = fadeIn(), exit = fadeOut()
            ) {
                Text(
                    text = title,
                    style = DoctaTypography.titleLarge,
                    color = DoctaColors.onSurface,
                    fontFamily = NotoSans,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier
                        .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                        .padding(vertical = 16.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GlassSurface(
                    modifier = Modifier.weight(1f, fill = fillGlassSurface),
                    filledWidths = glassSurfaceFilledWidths,
                    content = glassSurfaceContent
                )
                buttonUnderGlassSurface?.invoke()
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        AnimatedVisibility(
            visible = !keyboardInFocus, enter = fadeIn(), exit = fadeOut()
        ) {
            bottomButton()
        }

    }
}