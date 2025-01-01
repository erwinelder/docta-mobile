package cz.cvut.docta.question.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.answer.presentation.component.field.OpenAnswerTextField
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput

@Composable
fun OpenAnswerQuestionComponent(
    questionWithAnswerInput: QuestionWithAnswerInput.OpenAnswer,
    onAnswerChange: (String) -> Unit,
    onCheckButtonClick: () -> Unit
) {
    QuestionComponentContainerWithCheckButton(
        question = questionWithAnswerInput.question,
        checkIsAllowed = questionWithAnswerInput.checkIsAllowed,
        onCheckButtonClick = onCheckButtonClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = questionWithAnswerInput.question.text,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth(FilledWidthByScreenType(.82f).getByType(CurrWindowType)),
                fontFamily = Manrope,
                fontWeight = FontWeight.W600
            )
            OpenAnswerTextField(
                text = questionWithAnswerInput.answerInput.answer,
                onValueChange = onAnswerChange
            )
        }
    }
}