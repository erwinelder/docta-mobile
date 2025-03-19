package cz.cvut.docta.lessonSession.presentation.screen

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
import cz.cvut.docta.SharedRes
import cz.cvut.docta.lessonSession.presentation.component.field.answer.QuestionTextBlankField
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FillInBlanksQuestionScreen(
    screenPadding: PaddingValues,
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
        questionInstructions = stringResource(SharedRes.strings.fill_in_blanks_question_instructions),
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