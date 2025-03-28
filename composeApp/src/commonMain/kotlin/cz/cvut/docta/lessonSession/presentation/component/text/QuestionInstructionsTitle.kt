package cz.cvut.docta.lessonSession.presentation.component.text

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

@Composable
fun QuestionInstructionsTitle(questionInstructions: String) {
    Row(
        modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
    ) {
        Text(
            text = questionInstructions,
            color = DoctaColors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = NotoSans,
            letterSpacing = 0.1.sp
        )
    }
}