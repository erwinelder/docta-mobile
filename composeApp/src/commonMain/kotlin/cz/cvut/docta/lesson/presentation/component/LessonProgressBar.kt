package cz.cvut.docta.lesson.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.lesson.presentation.model.LessonProgression

@Composable
fun LessonProgressBar(
    progression: LessonProgression?,
    height: Dp = 16.dp
) {
    AnimatedVisibility(
        visible = progression != null,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            progression?.let {
                val percentage by animateFloatAsState(
                    targetValue = progression.getProgress()
                )

                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                        .height(height)
                ) {
                    Spacer(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(DoctaColors.glassSurfaceGradient[0])
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    Spacer(
                        modifier = Modifier
                            .shadow(
                                elevation = height / 2,
                                spotColor = DoctaColors.primary,
                                shape = RoundedCornerShape(50)
                            )
                            .clip(RoundedCornerShape(50))
                            .background(brush = Brush.linearGradient(DoctaColors.primaryGradient))
                            .fillMaxWidth(percentage)
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}