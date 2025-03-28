package cz.cvut.docta.course.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.presentation.theme.DoctaColors
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.checked_icon
import docta.composeapp.generated.resources.long_right_arrow_icon
import docta.composeapp.generated.resources.reset_icon
import org.jetbrains.compose.resources.DrawableResource

sealed class CourseUnitCompletionMarkState(
    open val gradientColor: List<Color>,
    open val contentColor: Color,
    open val iconRes: DrawableResource,
    open val iconDescription: String
) {

    data class NotStarted(
        override val gradientColor: List<Color>,
        override val contentColor: Color,
        override val iconRes: DrawableResource,
        override val iconDescription: String
    ) : CourseUnitCompletionMarkState(gradientColor, contentColor, iconRes, iconDescription) {

        companion object {

            @Composable
            fun initialize(): NotStarted {
                return NotStarted(
                    gradientColor = DoctaColors.disabledSemiTransparentGradient,
                    contentColor = DoctaColors.onSurface,
                    iconRes = Res.drawable.long_right_arrow_icon,
                    iconDescription = "start icon"
                )
            }

        }

    }

    data class InProgress(
        override val gradientColor: List<Color>,
        override val contentColor: Color,
        override val iconRes: DrawableResource,
        override val iconDescription: String,
        val nextStep: Int
    ) : CourseUnitCompletionMarkState(gradientColor, contentColor, iconRes, iconDescription) {

        companion object {

            @Composable
            fun initialize(nextStep: Int): InProgress {
                return InProgress(
                    gradientColor = DoctaColors.disabledSemiTransparentGradient,
                    contentColor = DoctaColors.onSurface,
                    iconRes = Res.drawable.long_right_arrow_icon,
                    iconDescription = "continue icon",
                    nextStep = nextStep
                )
            }

        }

    }

    data class Completed(
        override val gradientColor: List<Color>,
        override val contentColor: Color,
        override val iconRes: DrawableResource,
        override val iconDescription: String,
        val icon: CourseUnitCompletedIcon
    ) : CourseUnitCompletionMarkState(gradientColor, contentColor, iconRes, iconDescription) {

        enum class CourseUnitCompletedIcon {
            Checked, Repeat
        }

        companion object {

            @Composable
            fun initialize(icon: CourseUnitCompletedIcon): Completed {
                return Completed(
                    gradientColor = DoctaColors.primaryGradient,
                    contentColor = DoctaColors.onPrimary,
                    iconRes = when (icon) {
                        CourseUnitCompletedIcon.Checked -> Res.drawable.checked_icon
                        CourseUnitCompletedIcon.Repeat -> Res.drawable.reset_icon
                    },
                    iconDescription = when (icon) {
                        CourseUnitCompletedIcon.Checked -> "completed icon"
                        CourseUnitCompletedIcon.Repeat -> "repeat icon"
                    },
                    icon = icon
                )
            }
        }

    }

}