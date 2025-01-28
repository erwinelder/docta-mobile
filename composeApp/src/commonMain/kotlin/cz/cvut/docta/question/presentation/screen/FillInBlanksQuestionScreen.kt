package cz.cvut.docta.question.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.answer.presentation.component.field.QuestionTextBlankField
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.question.domain.model.QuestionCheckResult
import cz.cvut.docta.question.presentation.component.screen_container.QuestionScreenContainer
import cz.cvut.docta.question.presentation.model.QuestionBlankUnit
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FillInBlanksQuestionScreen(
    screenPadding: PaddingValues,
    questionInstructionsRes: StringResource,
    questionUnits: List<QuestionBlankUnit>,
    blanksAnswers: Map<Int, String>,
    onBlankAnswerChange: (Int, String) -> Unit,
    checkIsAllowed: Boolean,
    checkResult: QuestionCheckResult?,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(questionInstructionsRes),
        buttonIsEnabled = checkIsAllowed,
        showCheckButton = checkResult == null,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) {
        AnimatedContent(
            targetState = checkResult,
        ) { targetCheckResult ->
            if (targetCheckResult == null) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                ) {
                    questionUnits.forEach { unit ->
                        when (unit) {
                            is QuestionBlankUnit.BlankNumber -> QuestionTextBlankField(
                                text = blanksAnswers[unit.number] ?: "",
                                onValueChange = {
                                    onBlankAnswerChange(unit.number, it)
                                }
                            )
                            is QuestionBlankUnit.Word -> Text(
                                text = unit.word,
                                fontSize = 17.sp,
                                color = DoctaColors.onSurface,
                                fontWeight = FontWeight.Medium,
                                fontFamily = Manrope,
                                modifier = Modifier.padding(vertical = 9.dp)
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = if (targetCheckResult.isCorrect) "Correct" else "Incorrect",
                )
            }
        }
    }
}