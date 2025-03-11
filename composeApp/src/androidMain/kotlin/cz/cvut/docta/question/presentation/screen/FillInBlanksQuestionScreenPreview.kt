package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.question.domain.model.QuestionCheckResult
import cz.cvut.docta.question.presentation.model.QuestionBlankUnit

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
    checkIsAllowed: Boolean = true,
    checkResult: QuestionCheckResult? = null
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        FillInBlanksQuestionScreen(
            screenPadding = PaddingValues(),
            questionUnits = questionUnits,
            blanksAnswers = blanksAnswers,
            onBlankAnswerChange = { _, _ ->},
            checkIsAllowed = checkIsAllowed,
            checkResult = checkResult,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}