package cz.cvut.docta.lessonSession.presentation.component.container.question

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun QuestionText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        color = DoctaColors.onSurface,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Manrope,
        modifier = Modifier.fillMaxWidth(
            FilledWidthByScreenType(.82f).getByType(CurrWindowType)
        )
    )
}