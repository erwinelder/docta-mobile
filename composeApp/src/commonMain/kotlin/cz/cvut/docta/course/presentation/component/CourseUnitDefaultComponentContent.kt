package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.course.presentation.model.CourseUnitCompletionMarkState

@Composable
fun CourseUnitDefaultComponentContent(
    unitName: String,
    completionState: CourseUnitCompletionMarkState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Companion.CenterVertically,
        modifier = Modifier.Companion
            .height(64.dp)
            .padding(start = 24.dp)
    ) {
        CourseUnitNameComponent(
            name = unitName,
            modifier = Modifier.Companion
                .weight(1f)
                .padding(vertical = 16.dp)
        )
        CourseUnitCompletionMarkComponent(completionState = completionState)
    }
}