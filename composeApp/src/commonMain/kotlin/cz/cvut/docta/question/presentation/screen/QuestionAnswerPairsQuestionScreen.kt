package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.answer.presentation.component.container.QuestionAnswerPairsColumn
import cz.cvut.docta.answer.presentation.model.QuestionAnswerPairItemUiState
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.question.presentation.component.screen_container.QuestionScreenContainer
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun QuestionAnswerPairsQuestionScreen(
    screenPadding: PaddingValues,
    questions: List<QuestionAnswerPairItemUiState>,
    answers: List<QuestionAnswerPairItemUiState>,
    onQuestionSelect: (Long) -> Unit,
    onAnswerSelect: (Long) -> Unit,
    continueButtonEnabled: Boolean,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.question_answer_pairs_question_instructions),
        buttonIsEnabled = continueButtonEnabled,
        showCheckButton = false,
        onContinueButtonClick = onContinueButtonClick
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
        ) {
            QuestionAnswerPairsColumn(items = questions, onItemSelect = onQuestionSelect)
            QuestionAnswerPairsColumn(items = answers, onItemSelect = onAnswerSelect)
        }
    }
}