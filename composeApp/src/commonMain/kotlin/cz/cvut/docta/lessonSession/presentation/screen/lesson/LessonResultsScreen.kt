package cz.cvut.docta.lessonSession.presentation.screen.lesson

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.core.presentation.theme.NotoSans
import cz.cvut.docta.errorHandling.presentation.component.screenContainer.AnimatedRequestStateScreen
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.lessonSession.presentation.model.LessonStatsUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun LessonResultsScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    requestState: RequestState?,
    lessonStatsUiState: LessonStatsUiState?,
    onContinueButtonClick: () -> Unit,
    onErrorClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(screenPadding)
    ) {

        ScreenContainer(
            padding = PaddingValues(vertical = 24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LessonStatsBlock(lessonStatsUiState = lessonStatsUiState)
            }
            AnimatedContent(
                targetState = requestState
            ) { state ->
                if (state == null) {
                    PrimaryButton(
                        text = stringResource(SharedRes.strings.continue_),
                        onClick = onContinueButtonClick
                    )
                }
            }
        }

        AnimatedRequestStateScreen(
            screenPadding = screenPadding,
            requestState = requestState,
            onSuccessClose = {},
            onErrorClose = onErrorClose
        )

    }
}

@Composable
private fun ColumnScope.LessonStatsBlock(
    lessonStatsUiState: LessonStatsUiState?
) {

    Box {
        Text(
            text = "-",
            color = Color.Transparent,
            fontSize = 96.sp,
            fontWeight = FontWeight.W900,
            fontFamily = NotoSans
        )
        Row {
            DigitText(text = lessonStatsUiState?.firstDigit ?: "", orderNum = 3)
            DigitText(text = lessonStatsUiState?.secondDigit ?: "", orderNum = 2)
            DigitText(text = lessonStatsUiState?.thirdDigit ?: "", orderNum = 1)
            DigitText(
                text = "%".takeIf { lessonStatsUiState != null } ?: "",
                orderNum = if (lessonStatsUiState?.digitCount == 3) 4 else 3
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    AnimatedContent(
        targetState = lessonStatsUiState
    ) { lessonStats ->
        if (lessonStats != null) {
            Text(
                text = stringResource(lessonStats.messageRes),
                color = DoctaColors.onSurface,
                fontSize = 19.sp,
                fontWeight = FontWeight.W500,
                fontFamily = Manrope
            )
        }
    }

}

@Composable
private fun DigitText(text: String, orderNum: Int) {
    AnimatedContent(
        targetState = text,
        transitionSpec = {
            enterTransitionWithDelay(150 * orderNum + 500).togetherWith(fadeOut())
        },
    ) { digit ->
        Text(
            text = digit,
            color = DoctaColors.onSurface,
            fontSize = 96.sp,
            fontWeight = FontWeight.W900,
            fontFamily = NotoSans
        )
    }

}

private fun enterTransitionWithDelay(delayMillis: Int = 0): EnterTransition {
    return fadeIn(tween(400, delayMillis)) +
            slideInVertically(tween(400, delayMillis)) { -(it * 0.3).toInt() }
}