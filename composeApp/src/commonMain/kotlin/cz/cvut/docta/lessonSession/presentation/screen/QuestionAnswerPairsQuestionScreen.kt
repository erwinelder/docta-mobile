package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.component.container.answer.QuestionAnswerPairsColumn
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.QuestionAnswerPairItemUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun QuestionAnswerPairsQuestionScreen(
    screenPadding: PaddingValues,
    questions: List<QuestionAnswerPairItemUiState>,
    questionMaterials: List<Materials>,
    answers: List<QuestionAnswerPairItemUiState>,
    onQuestionSelect: (Long) -> Unit,
    onAnswerSelect: (Long) -> Unit,
    checkRequestState: AnswerCheckRequestState<AnswerCheckResult.QuestionAnswerPairs>,
    continueButtonEnabled: Boolean,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.question_answer_pairs_question_instructions),
        questionMaterials = questionMaterials,
        buttonIsEnabled = continueButtonEnabled,
        checkRequestState = checkRequestState,
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