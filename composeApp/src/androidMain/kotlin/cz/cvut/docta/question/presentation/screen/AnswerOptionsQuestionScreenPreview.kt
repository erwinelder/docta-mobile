package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.answer.presentation.model.AnswerOptionUiState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.question.domain.model.QuestionCheckResult

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun AnswerOptionsQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "What is the capital of France?",
    options: List<AnswerOptionUiState> = listOf(
        AnswerOptionUiState(id = 1, text = "Paris", isSelected = true),
        AnswerOptionUiState(id = 2, text = "Berlin", isSelected = false),
        AnswerOptionUiState(id = 3, text = "London", isSelected = false),
        AnswerOptionUiState(id = 4, text = "Madrid", isSelected = false)
    ),
    checkIsAllowed: Boolean = true,
    checkResult: QuestionCheckResult? = null
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        AnswerOptionsQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            options = options,
            onOptionSelect = {},
            checkIsAllowed = checkIsAllowed,
            checkResult = checkResult,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}