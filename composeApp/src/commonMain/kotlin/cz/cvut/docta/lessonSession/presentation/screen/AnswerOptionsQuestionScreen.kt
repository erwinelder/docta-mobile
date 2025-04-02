package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.component.container.answer.AnswerOptionsListComponent
import cz.cvut.docta.lessonSession.presentation.component.container.question.QuestionText
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AnswerOptionsQuestionScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    questionMaterials: List<Materials>,
    questionText: String,
    options: List<AnswerOptionUiState>,
    onOptionSelect: (Long) -> Unit,
    checkIsAllowed: Boolean,
    checkRequestState: AnswerCheckRequestState<AnswerCheckResult.SingleOption>,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.answer_option_question_instructions),
        questionMaterials = questionMaterials,
        buttonIsEnabled = checkIsAllowed,
        checkRequestState = checkRequestState,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) { checkState ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuestionText(text = questionText)
            AnswerOptionsListComponent(
                options = options,
                onOptionSelect = onOptionSelect,
                checkState = checkState
            )
        }
    }
}