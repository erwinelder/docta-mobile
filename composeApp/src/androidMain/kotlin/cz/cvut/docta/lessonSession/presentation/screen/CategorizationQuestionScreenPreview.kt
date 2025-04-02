package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.OptionWithCategory

@Preview(device = Devices.PIXEL_7_PRO, name = "CategorizationQuestionScreenPreview")
@Composable
fun CategorizationQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "Assign each class to a design pattern",
    categories: List<AnswerText> = listOf(
        AnswerText(
            id = 1,
            text = "Singleton"
        ),
        AnswerText(
            id = 2,
            text = "Factory Method",
        ),
        AnswerText(
            id = 3,
            text = "Builder",
        )
    ),
    optionsWithCategories: List<OptionWithCategory> = listOf(
        OptionWithCategory(
            optionId = 1,
            optionText = "ClassA",
            category = categories[0]
        ),
        OptionWithCategory(
            optionId = 2,
            optionText = "ClassB",
            category = categories[1]
        ),
        OptionWithCategory(
            optionId = 3,
            optionText = "ClassC",
            category = categories[2]
        )
    ),
    checkIsAllowed: Boolean = true
) {
    var isChecked by remember { mutableStateOf(false) }

    val checkStateIdle: AnswerCheckRequestState<AnswerCheckResult.CategorizedOptions> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Idle()
        )
    val checkStateResult: AnswerCheckRequestState<AnswerCheckResult.CategorizedOptions> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Result(
                result = AnswerCheckResult.CategorizedOptions(
                    isCorrect = false,
                    optionToCategory = mapOf(
                        1L to 1L,
                        2L to 3L,
                        3L to 3L
                    )
                )
            )
        )

    val checkRequestState = if (isChecked) checkStateResult else checkStateIdle

    ScreenPreviewContainer(appTheme = appTheme) {
        CategorizationQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            questionMaterials = emptyList(),
            optionsWithCategories = optionsWithCategories,
            categories = categories,
            onOptionCategorySelect = { _, _ -> },
            checkIsAllowed = checkIsAllowed,
            checkRequestState = checkRequestState,
            onCheckButtonClick = { isChecked = true },
            onContinueButtonClick = { isChecked = false }
        )
    }
}