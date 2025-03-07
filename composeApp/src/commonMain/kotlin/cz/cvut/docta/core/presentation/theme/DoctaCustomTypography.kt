package cz.cvut.docta.core.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class DoctaCustomTypography(
    val titleMedium: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center,
        lineHeight = 40.sp
    )
)