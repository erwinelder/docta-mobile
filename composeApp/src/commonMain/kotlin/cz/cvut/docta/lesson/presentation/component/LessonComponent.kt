package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.CurrAppTheme
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.presentation.utils.getLessonIconRes
import org.jetbrains.compose.resources.painterResource

@Composable
fun LessonComponent(
    state: Lesson,
    onClick: (Lesson) -> Unit,
    buttonVerticalPadding: Dp = 24.dp,
    content: @Composable RowScope.() -> Unit
) {
    GlassSurface(
        cornerSize = 30.dp,
        borderSize = 0.dp,
        modifier = Modifier.bounceClickEffect(.98f) {
            onClick(state)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Icon(
                painter = painterResource(state.getLessonIconRes().get(CurrAppTheme)),
                contentDescription = "one-step questions lesson icon",
                modifier = Modifier.size(40.dp)
            )
            content()
            LessonDoneOrStartComponent(
                isDone = state.isDone,
                verticalPadding = buttonVerticalPadding
            )
        }
    }
}