package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.course.domain.model.Course

@Composable
fun CourseComponent(
    course: Course,
    onClick: ((Course) -> Unit)? = null
) {
    GlassSurface(
        cornerSize = 20.dp,
        modifier = Modifier.bounceClickEffect {
            onClick?.invoke(course)
        }
    ) {
        Text(
            text = course.name,
            color = DoctaColors.onSurface,
            fontSize = 20.sp,
            fontFamily = Manrope,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp)
        )
    }
}