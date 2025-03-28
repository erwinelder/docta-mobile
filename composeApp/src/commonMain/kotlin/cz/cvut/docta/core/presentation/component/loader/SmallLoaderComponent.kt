package cz.cvut.docta.core.presentation.component.loader

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun SmallLoaderComponent(
    text: String,
    fontSize: TextUnit = 18.sp
) {
    Text(
        text = text,
        color = DoctaColors.outline,
        fontSize = fontSize,
        fontWeight = FontWeight.W500,
        fontFamily = Manrope
    )
}