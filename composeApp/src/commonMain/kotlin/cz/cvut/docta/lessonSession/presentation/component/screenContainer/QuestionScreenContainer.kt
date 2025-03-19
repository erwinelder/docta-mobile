package cz.cvut.docta.lessonSession.presentation.component.screenContainer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.utils.getImeBottomInset
import cz.cvut.docta.lessonSession.presentation.component.text.QuestionInstructionsTitle
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun QuestionScreenContainer(
    screenPadding: PaddingValues,
    questionInstructions: String,
    buttonIsEnabled: Boolean,
    showCheckButton: Boolean,
    onCheckButtonClick: () -> Unit = {},
    onContinueButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val imeBottomInset by getImeBottomInset()
    val bottomPadding by animateDpAsState(imeBottomInset.coerceAtLeast(24.dp))

    ScreenContainer(
        verticalArrangement = Arrangement.SpaceBetween,
        padding = PaddingValues(
            top = screenPadding.calculateTopPadding() + 24.dp,
            bottom = bottomPadding
        ),
        modifier = Modifier.clickable { focusManager.clearFocus() }
    ) {
        QuestionInstructionsTitle(questionInstructions = questionInstructions)
        content()
        PrimaryButton(
            text = stringResource(
                if (showCheckButton) SharedRes.strings.check else SharedRes.strings.continue_
            ),
            enabled = buttonIsEnabled,
            onClick = if (showCheckButton) onCheckButtonClick else
                onContinueButtonClick
        )
    }
}