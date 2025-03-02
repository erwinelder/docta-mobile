package cz.cvut.docta.question.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer
import cz.cvut.docta.question.presentation.model.QuestionBlankUnit

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun FillInBlanksQuestionScreenPreview() {
    ScreenPreviewContainer {
        FillInBlanksQuestionScreen(
            screenPadding = PaddingValues(),
            questionUnits = listOf(
                QuestionBlankUnit.Word("The"),
                QuestionBlankUnit.Word("capital"),
                QuestionBlankUnit.Word("of"),
                QuestionBlankUnit.BlankNumber(1),
                QuestionBlankUnit.Word("is"),
                QuestionBlankUnit.Word("Paris.")
            ),
            blanksAnswers = mapOf(1 to "France"),
            onBlankAnswerChange = { _, _ ->},
            checkIsAllowed = true,
            checkResult = null,
            onCheckButtonClick = {},
            onContinueButtonClick = {}
        )
    }
}