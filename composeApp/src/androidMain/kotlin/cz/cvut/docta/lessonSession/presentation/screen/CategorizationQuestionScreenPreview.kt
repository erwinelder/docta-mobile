package cz.cvut.docta.lessonSession.presentation.screen

import CategorizationQuestionScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.model.question.CategorizationOptionUiState
import cz.cvut.docta.lessonSession.presentation.model.question.CategoryUiState

@Preview(device = Devices.PIXEL_7_PRO, name = "CategorizationQuestionScreenPreview")
@Composable
fun CategorizationQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "Assign each class to a design pattern",
    options: List<CategorizationOptionUiState> = listOf(
        CategorizationOptionUiState(
            id = 101,
            text = "ClassA",
            selectedCategoryId = 1
        ),
        CategorizationOptionUiState(
            id = 102,
            text = "ClassB",
            selectedCategoryId = null
        ),
        CategorizationOptionUiState(
            id = 103,
            text = "ClassC",
            selectedCategoryId = 2
        )
    ),
    categories: List<CategoryUiState> = listOf(
        CategoryUiState(
            id = 1,
            name = "Singleton"
        ),
        CategoryUiState(
            id = 2,
            name = "Factory Method",
        ),
        CategoryUiState(
            id = 3,
            name = "Builder",
        )
    ),
    checkIsAllowed: Boolean = false,
    checkResult: QuestionCheckResult? = null
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        CategorizationQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            questionMaterials = emptyList(),
            options = options,
            categories = categories,
            onCategorySelect = { _, _ -> },
            checkIsAllowed = checkIsAllowed,
            checkResult = checkResult,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}