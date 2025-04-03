package cz.cvut.docta.lessonSession.presentation.screen.question

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
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SingleOptionQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "Which of the following is a unit testing framework for Kotlin?",
    options: List<AnswerOptionUiState> = listOf(
        AnswerOptionUiState(id = 1, text = "Kotest", isSelected = true),
        AnswerOptionUiState(id = 2, text = "JMeter", isSelected = false),
        AnswerOptionUiState(id = 3, text = "Postman", isSelected = false),
    ),
    checkIsAllowed: Boolean = true
) {
    var isChecked by remember { mutableStateOf(false) }

    val checkStateIdle: AnswerCheckRequestState<AnswerCheckResult.SingleOption> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Idle()
        )
    val checkStateResult: AnswerCheckRequestState<AnswerCheckResult.SingleOption> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Result(
                result = AnswerCheckResult.SingleOption(
                    isCorrect = false,
                    id = 2
                )
            )
        )

    val checkRequestState = if (isChecked) checkStateResult else checkStateIdle

    ScreenPreviewContainer(appTheme = appTheme) {
        SingleOptionQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            questionMaterials = emptyList(),
            options = options,
            onOptionSelect = {},
            checkIsAllowed = checkIsAllowed,
            checkRequestState = checkRequestState,
            onCheckButtonClick = { isChecked = true },
            onContinueButtonClick = { isChecked = false }
        )
    }
}