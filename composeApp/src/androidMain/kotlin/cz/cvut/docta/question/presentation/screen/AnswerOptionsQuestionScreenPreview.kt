package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.answer.presentation.model.AnswerOptionUiState
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.answer_option_question_instructions

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AnswerOptionsQuestionScreenPreview() {
    ScreenPreviewContainer {
        AnswerOptionsQuestionScreen(
            screenPadding = PaddingValues(),
            questionInstructionsRes = Res.string.answer_option_question_instructions,
            questionText = "What is the capital of France?",
            options = listOf(
                AnswerOptionUiState(
                    id = 1,
                    text = "Paris",
                    isSelected = true
                ),
                AnswerOptionUiState(
                    id = 2,
                    text = "Berlin",
                    isSelected = false
                ),
                AnswerOptionUiState(
                    id = 3,
                    text = "London",
                    isSelected = false
                ),
                AnswerOptionUiState(
                    id = 4,
                    text = "Madrid",
                    isSelected = false
                )
            ),
            onOptionSelect = {},
            checkIsAllowed = true,
            checkResult = null,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}