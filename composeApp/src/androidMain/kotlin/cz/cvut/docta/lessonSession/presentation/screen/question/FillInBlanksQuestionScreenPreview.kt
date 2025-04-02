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
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun FillInBlanksQuestionScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    questionUnits: List<QuestionBlankUnit> = listOf(
        QuestionBlankUnit.Word("The"),
        QuestionBlankUnit.Word("capital"),
        QuestionBlankUnit.Word("of"),
        QuestionBlankUnit.BlankNumber(1),
        QuestionBlankUnit.Word("is"),
        QuestionBlankUnit.Word("Paris.")
    ),
    blanksAnswers: Map<Int, String> = mapOf(1 to "France"),
    checkIsAllowed: Boolean = true
) {
    var isChecked by remember { mutableStateOf(false) }

    val checkStateIdle: AnswerCheckRequestState<AnswerCheckResult.Blanks> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Idle()
        )
    val checkStateResult: AnswerCheckRequestState<AnswerCheckResult.Blanks> =
        AnswerCheckRequestState.Default(
            state = AnswerCheckState.Result(
                result = AnswerCheckResult.Blanks(
                    isCorrect = false,
                    answers = mapOf(
                        1 to "France"
                    )
                )
            )
        )

    val checkRequestState = if (isChecked) checkStateResult else checkStateIdle

    ScreenPreviewContainer(appTheme = appTheme) {
        FillInBlanksQuestionScreen(
            screenPadding = PaddingValues(),
            questionUnits = questionUnits,
            questionMaterials = emptyList(),
            blanksAnswers = blanksAnswers,
            onBlankAnswerChange = { _, _ -> },
            checkIsAllowed = checkIsAllowed,
            checkRequestState = checkRequestState,
            onCheckButtonClick = { isChecked = true },
            onContinueButtonClick = { isChecked = false }
        )
    }
}