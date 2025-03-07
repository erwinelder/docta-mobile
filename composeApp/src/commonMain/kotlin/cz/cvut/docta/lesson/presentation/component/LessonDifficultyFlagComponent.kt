package cz.cvut.docta.lesson.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.presentation.utils.asColor
import cz.cvut.docta.lesson.presentation.utils.asStringRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun LessonDifficultyFlagComponent(difficulty: LessonDifficulty) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .background(color = difficulty.asColor(), shape = RoundedCornerShape(50))
                .size(8.dp)
        )
        Text(
            text = stringResource(difficulty.asStringRes()),
            color = difficulty.asColor(),
            fontSize = 15.sp,
            fontFamily = Manrope
        )
    }
}