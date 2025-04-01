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
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun OpenAnswerQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionText: String = "What is the capital of France?",
    answerInput: String = "The capital of France is Berlin.",
    checkIsAllowed: Boolean = true
) {
    var isChecked by remember { mutableStateOf(false) }

    val checkStateIdle: AnswerCheckRequestState<AnswerCheckResult.Open> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Idle()
        )
    val checkStateResult: AnswerCheckRequestState<AnswerCheckResult.Open> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Result(
                result = AnswerCheckResult.Open(
                    isCorrect = false,
                    answer = "The capital of France is Paris."
                )
            )
        )

    val checkRequestState = if (isChecked) checkStateResult else checkStateIdle

    ScreenPreviewContainer(appTheme = appTheme) {
        OpenAnswerQuestionScreen(
            screenPadding = PaddingValues(),
            questionText = questionText,
            questionMaterials = listOf(
                Materials(id = 1, text = "Materials text of 1."),
                Materials(id = 2, text = "Materials text of 2, which is longer."),
                Materials(id = 3, text = "Materials text of 3, which is much much longer than in first one."),
                Materials(id = 4, text = "Materials text of 4, which should be the longest of them all, longer than in first and second one.")
            ),
            answerText = answerInput,
            onAnswerChange = {},
            checkIsAllowed = checkIsAllowed,
            checkRequestState = checkRequestState,
            onCheckButtonClick = { isChecked = true },
            onContinueButtonClick = { isChecked = false }
        )
    }
}