package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.lessonSession.presentation.component.container.answer.AnswerOptionsList
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerOptionUiState
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun AnswerOptionsQuestionScreen(
    screenPadding: PaddingValues,
    questionText: String,
    options: List<AnswerOptionUiState>,
    onOptionSelect: (Long) -> Unit,
    checkIsAllowed: Boolean,
    checkResult: QuestionCheckResult?,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.answer_option_question_instructions),
        buttonIsEnabled = checkIsAllowed,
        showCheckButton = checkResult == null,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) {
        AnimatedContent(
            targetState = checkResult
        ) { targetCheckResult ->
            if (targetCheckResult == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = questionText,
                        fontSize = 18.sp,
                        color = DoctaColors.onSurface,
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(
                            FilledWidthByScreenType(.82f).getByType(CurrWindowType)
                        )
                    )
                    AnswerOptionsList(
                        options = options,
                        onOptionSelect = onOptionSelect
                    )
                }
            } else {
                Text(
                    text = if (targetCheckResult.isCorrect) "Correct" else "Incorrect",
                )
            }
        }
    }
}