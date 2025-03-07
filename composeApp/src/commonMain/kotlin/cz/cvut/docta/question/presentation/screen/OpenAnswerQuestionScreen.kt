package cz.cvut.docta.question.presentation.screen

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
import cz.cvut.docta.answer.presentation.component.field.OpenAnswerTextField
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.question.domain.model.QuestionCheckResult
import cz.cvut.docta.question.presentation.component.screen_container.QuestionScreenContainer
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OpenAnswerQuestionScreen(
    screenPadding: PaddingValues,
    questionText: String,
    answerInput: String,
    onAnswerChange: (String) -> Unit,
    checkIsAllowed: Boolean,
    checkResult: QuestionCheckResult?,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.open_answer_question_instructions),
        buttonIsEnabled = checkIsAllowed,
        showCheckButton = checkResult == null,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) {
        AnimatedContent(
            targetState = checkResult,
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
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Manrope,
                        modifier = Modifier.fillMaxWidth(
                            FilledWidthByScreenType(.82f).getByType(CurrWindowType)
                        )
                    )
                    OpenAnswerTextField(
                        text = answerInput,
                        onValueChange = onAnswerChange
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