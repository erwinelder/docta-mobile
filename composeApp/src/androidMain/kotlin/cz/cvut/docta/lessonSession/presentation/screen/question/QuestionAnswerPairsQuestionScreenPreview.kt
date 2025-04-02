package cz.cvut.docta.lessonSession.presentation.screen.question

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.lessonSession.presentation.model.answer.QuestionAnswerPairItemUiState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun QuestionAnswerPairsQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questions: List<QuestionAnswerPairItemUiState> = listOf(
        QuestionAnswerPairItemUiState(
            id = 1,
            text = "question 1 question 1 question 1",
            isSelected = true,
            isCorrect = null,
            isDisabled = false
        ),
        QuestionAnswerPairItemUiState(
            id = 2,
            text = "question 2",
            isSelected = false,
            isCorrect = null,
            isDisabled = true
        ),
        QuestionAnswerPairItemUiState(
            id = 3,
            text = "question 3",
            isSelected = false,
            isCorrect = true,
            isDisabled = false
        ),
        QuestionAnswerPairItemUiState(
            id = 4,
            text = "question 4",
            isSelected = false,
            isCorrect = null,
            isDisabled = false
        ),
    ),
    answers: List<QuestionAnswerPairItemUiState> = listOf(
        QuestionAnswerPairItemUiState(
            id = 1,
            text = "answer 1",
            isSelected = false,
            isCorrect = null,
            isDisabled = false
        ),
        QuestionAnswerPairItemUiState(
            id = 2,
            text = "answer 2",
            isSelected = false,
            isCorrect = null,
            isDisabled = false
        ),
        QuestionAnswerPairItemUiState(
            id = 3,
            text = "answer 3",
            isSelected = false,
            isCorrect = null,
            isDisabled = true
        ),
        QuestionAnswerPairItemUiState(
            id = 4,
            text = "answer 4",
            isSelected = false,
            isCorrect = false,
            isDisabled = false
        ),
    ),
    continueButtonEnabled: Boolean = true
) {
    val checkStateIdle: AnswerCheckRequestState<AnswerCheckResult.QuestionAnswerPairs> =
        AnswerCheckRequestState.Default(state = AnswerCheckState.Idle())

    ScreenPreviewContainer(appTheme = appTheme) {
        QuestionAnswerPairsQuestionScreen(
            screenPadding = PaddingValues(),
            questions = questions,
            questionMaterials = emptyList(),
            answers = answers,
            onQuestionSelect = {},
            onAnswerSelect = {},
            checkRequestState = checkStateIdle,
            continueButtonEnabled = continueButtonEnabled,
            onContinueButtonClick = {}
        )
    }
}