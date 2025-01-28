package cz.cvut.docta.answer.presentation.component.field

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.field.DoctaTextField

@Composable
fun QuestionTextBlankField(
    text: String,
    onValueChange: (String) -> Unit
) {
    DoctaTextField(
        text = text,
        onValueChange = onValueChange,
        modifier = Modifier.padding(horizontal = 5.dp),
        fontSize = 18.sp,
        cornerSize = 12.dp
    )
}