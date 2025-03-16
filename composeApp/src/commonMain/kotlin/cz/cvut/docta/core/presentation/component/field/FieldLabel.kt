package cz.cvut.docta.core.presentation.component.field

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun FieldLabel(
    text: String,
    fontSize: TextUnit = 15.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = DoctaColors.outline,
        fontWeight = FontWeight.W500,
        fontFamily = Manrope,
        textAlign = TextAlign.Center
    )
}