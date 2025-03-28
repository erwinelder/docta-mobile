package cz.cvut.docta.course.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun CourseUnitNameComponent(
    name: String,
    modifier: Modifier = Modifier.Companion
) {
    Text(
        text = name,
        color = DoctaColors.onSurface,
        fontFamily = Manrope,
        overflow = TextOverflow.Companion.Ellipsis,
        maxLines = 1,
        style = DoctaTypography.courseUnitName,
        modifier = modifier
    )
}