package cz.cvut.docta.lessonSession.presentation.component.field.answer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.field.SmallTextField
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.presentation.model.question.QuestionBlankUnit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestionTextBlankField(
    text: String,
    onValueChange: (String) -> Unit,
    isLast: Boolean,
    blanksAnswers: Map<Int, String>,
    blankUnit: QuestionBlankUnit.BlankNumber,
    checkState: AnswerCheckResult.Blanks?,
    readOnly: Boolean
) {
    if (checkState == null) {

        SmallTextField(
            text = text,
            onValueChange = onValueChange,
            modifier = Modifier.padding(horizontal = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            cornerSize = 12.dp,
            imeAction = if (isLast) ImeAction.Done else ImeAction.Next,
            readOnly = readOnly
        )

    } else {

        val textColor = if (checkState.isCorrect)
            DoctaColors.successGlass else DoctaColors.errorGlass

        GlassSurface(
            filledWidths = null,
            cornerSize = 12.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Text(
                    text = blanksAnswers[blankUnit.number] ?: "",
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = Manrope,
                    textDecoration = TextDecoration.LineThrough
                        .takeUnless { checkState.isCorrect },
                    modifier = Modifier.padding(vertical = 6.dp)
                )
                if (!checkState.isCorrect) {
                    Text(
                        text = checkState.answers[blankUnit.number] ?: "",
                        color = DoctaColors.successGlass,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        fontFamily = Manrope,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }
        }

    }
}