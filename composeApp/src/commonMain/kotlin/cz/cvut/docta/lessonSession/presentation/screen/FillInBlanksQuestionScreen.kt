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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.component.field.answer.QuestionTextBlankField
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FillInBlanksQuestionScreen(
    screenPadding: PaddingValues,
    questionMaterials: List<Materials>,
    questionUnits: List<QuestionBlankUnit>,
    blanksAnswers: Map<Int, String>,
    onBlankAnswerChange: (Int, String) -> Unit,
    checkIsAllowed: Boolean,
    checkRequestState: AnswerCheckRequestState<AnswerCheckResult.Blanks>,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    val questionUnitsLastIndex by remember(questionUnits) {
        derivedStateOf { questionUnits.lastIndex }
    }

    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.fill_in_blanks_question_instructions),
        questionMaterials = questionMaterials,
        buttonIsEnabled = checkIsAllowed,
        checkRequestState = checkRequestState,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) { checkState ->
        AnimatedContent(
            targetState = checkState
        ) { targetCheckState ->
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(
                    FilledWidthByScreenType().getByType(CurrWindowType)
                )
            ) {
                questionUnits.forEachIndexed { index, unit ->
                    when (unit) {
                        is QuestionBlankUnit.BlankNumber -> {
                            QuestionTextBlankField(
                                text = blanksAnswers[unit.number] ?: "",
                                onValueChange = {
                                    onBlankAnswerChange(unit.number, it)
                                },
                                isLast = index == questionUnitsLastIndex,
                                blanksAnswers = blanksAnswers,
                                blankUnit = unit,
                                checkState = targetCheckState.getResultOrNull(),
                                readOnly = targetCheckState !is AnswerCheckState.Idle
                            )
                        }
                        is QuestionBlankUnit.Word -> Text(
                            text = unit.word,
                            fontSize = 18.sp,
                            color = DoctaColors.onSurface,
                            fontWeight = FontWeight.W500,
                            fontFamily = Manrope,
                            modifier = Modifier.padding(vertical = 9.dp)
                        )
                    }
                }
            }
        }
    }
}