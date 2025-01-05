package cz.cvut.docta.question.presentation.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.NotoSans
import cz.cvut.docta.question.domain.model.Question

@Composable
fun QuestionInstructionsTitle(question: Question) {
    Row(
        modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
    ) {
        Text(
            // TODO-STRING-RESOURCE
            text = when (question) {
                is Question.OpenAnswer -> "Answer the question"
                is Question.FillInBlanks -> "Fill in the blanks"
                is Question.AnswerOptions -> "Choose the correct answer"
                is Question.QuestionAnswerPairs -> "Match the pairs"
                is Question.StepByStep -> "Solve this step of the task"
            },
            color = DoctaColors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = NotoSans,
            letterSpacing = 0.1.sp
        )
    }
}