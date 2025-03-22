package cz.cvut.docta.course.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course.domain.model.CourseWithProgress

@Preview(device = PIXEL_7_PRO)
@Composable
fun CourseWithProgressComponentPreview(
    appTheme: AppTheme = AppTheme.Light
) {
    PreviewContainer(appTheme = appTheme) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CourseWithProgressComponent(
                course = CourseWithProgress.Completed(
                    code = "code",
                    locale = CourseLocale.English,
                    name = "Completed course name",
                    sectionCount = 14
                ),
                onClick = {}
            )
            CourseWithProgressComponent(
                course = CourseWithProgress.InProgress(
                    code = "code",
                    locale = CourseLocale.English,
                    name = "In progress course name",
                    sectionCount = 14,
                    completed = 10
                ),
                onClick = {}
            )
            CourseWithProgressComponent(
                course = CourseWithProgress.NotStarted(
                    code = "code",
                    locale = CourseLocale.English,
                    name = "Not started course name",
                    sectionCount = 14
                ),
                onClick = {}
            )
        }
    }
}