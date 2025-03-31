package cz.cvut.docta.lessonSession.presentation.component.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.NotoSans

@Composable
fun QuestionInstructionsTitleWithMaterials(
    questionInstructions: String,
    modifier: Modifier = Modifier
) {
        Text(
            text = questionInstructions,
            modifier = modifier,
            color = DoctaColors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = NotoSans,
            letterSpacing = 0.1.sp
        )
}