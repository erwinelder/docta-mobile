package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.answer.presentation.model.QuestionAnswerPairItemUiState
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun QuestionAnswerPairsQuestionScreenPreview() {
    ScreenPreviewContainer {
        QuestionAnswerPairsQuestionScreen(
            screenPadding = PaddingValues(),
            questions = listOf(
                QuestionAnswerPairItemUiState(
                    id = 1,
                    text = "question 1",
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
            answers = listOf(
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
            onQuestionSelect = {},
            onAnswerSelect = {},
            continueButtonEnabled = true,
            onContinueButtonClick = {}
        )
    }
}