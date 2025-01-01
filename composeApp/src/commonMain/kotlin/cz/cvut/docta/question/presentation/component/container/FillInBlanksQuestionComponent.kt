package cz.cvut.docta.question.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
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
import cz.cvut.docta.question.domain.model.FillInBlankQuestionUnit
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FillInBlanksQuestionComponent(
    questionWithAnswerInput: QuestionWithAnswerInput.FillInBlanks,
    onValueChange: (Int, String) -> Unit,
    onCheckButtonClick: () -> Unit
) {
    QuestionComponentContainerWithCheckButton(
        question = questionWithAnswerInput.question,
        checkIsAllowed = questionWithAnswerInput.checkIsAllowed,
        onCheckButtonClick = onCheckButtonClick
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
        ) {
            questionWithAnswerInput.question.units.forEach { unit ->
                when (unit) {
                    is FillInBlankQuestionUnit.BlankNumber -> {
                        QuestionTextBlankField(
                            text = questionWithAnswerInput.answerInput.getAnswerText(unit.number),
                            onValueChange = {
                                onValueChange(unit.number, it)
                            }
                        )
                    }
                    is FillInBlankQuestionUnit.Word -> {
                        Text(
                            text = unit.word,
                            color = DoctaColors.onSurface,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            fontFamily = Manrope,
                        )
                    }
                }
            }
        }
    }
}