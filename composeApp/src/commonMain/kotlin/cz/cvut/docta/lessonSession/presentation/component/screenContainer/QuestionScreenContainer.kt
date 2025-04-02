package cz.cvut.docta.lessonSession.presentation.component.screenContainer

import androidx.compose.animation.AnimatedVisibility
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
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.utils.getImeBottomInset
import cz.cvut.docta.errorHandling.presentation.component.container.ResultStateComponent
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.component.container.question.QuestionScreenTopBar
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun <T : AnswerCheckResult> QuestionScreenContainer(
    screenPadding: PaddingValues,
    questionInstructions: String,
    questionMaterials: List<Materials>,
    buttonIsEnabled: Boolean,
    checkRequestState: AnswerCheckRequestState<T>,
    onCheckButtonClick: () -> Unit = {},
    onContinueButtonClick: () -> Unit,
    content: @Composable (AnswerCheckState<T>) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val imeBottomInset by getImeBottomInset()
    val bottomPadding by animateDpAsState(imeBottomInset.coerceAtLeast(24.dp))

    val buttonTextRes = checkRequestState.getButtonStringRes()

    ScreenContainer(
        screenPadding = screenPadding,
        padding = PaddingValues(top = 24.dp, bottom = bottomPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.clickable { focusManager.clearFocus() }
    ) {
        QuestionScreenTopBar(
            questionInstructions = questionInstructions,
            materials = questionMaterials
        )
        AnimatedVisibility(
            visible = checkRequestState is AnswerCheckRequestState.Default,
        ) {
            (checkRequestState as? AnswerCheckRequestState.Default)?.let {
                content(it.state)
            }
        }
        AnimatedVisibility(
            visible = checkRequestState is AnswerCheckRequestState.Error,
        ) {
            (checkRequestState as? AnswerCheckRequestState.Error)?.let {
                ResultStateComponent(resultState = it.error)
            }
        }
        PrimaryButton(
            text = stringResource(buttonTextRes),
            enabled = buttonIsEnabled && checkRequestState.isNotLoading(),
            onClick = if (checkRequestState.shouldDisplayContinueButton())
                onContinueButtonClick else onCheckButtonClick
        )
    }
}
