package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState
import org.jetbrains.compose.resources.painterResource

@Composable
fun CourseUnitCompletionMarkComponent(
    completionState: CourseUnitCompletionMarkState
) {
    val iconSize = if (completionState is CourseUnitCompletionMarkState.InProgress) 24.dp else 26.dp
    val horizontalPadding = if (completionState is CourseUnitCompletionMarkState.InProgress) 17.dp else 16.dp

    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.Companion
            .background(
                brush = Brush.Companion.linearGradient(
                    colors = completionState.gradientColor,
                    start = Offset(75f, 210f),
                    end = Offset(95f, -10f)
                )
            )
            .fillMaxHeight()
            .padding(horizontal = horizontalPadding, vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(completionState.iconRes),
            contentDescription = completionState.iconDescription,
            tint = completionState.contentColor,
            modifier = Modifier.Companion.size(iconSize)
        )
        if (completionState is CourseUnitCompletionMarkState.InProgress) {
            Text(
                text = completionState.nextStep.toString(),
                color = completionState.contentColor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Companion.W500,
                fontFamily = Manrope
            )
        }
    }
}