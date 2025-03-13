package cz.cvut.docta.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.course.presentation.screen.AddNewCourseScreenPreview
import cz.cvut.docta.course.presentation.screen.CoursesScreenPreview
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreenPreview
import cz.cvut.docta.question.presentation.screen.AnswerOptionsQuestionScreenPreview
import cz.cvut.docta.question.presentation.screen.FillInBlanksQuestionScreenPreview
import cz.cvut.docta.question.presentation.screen.OpenAnswerQuestionScreenPreview
import cz.cvut.docta.question.presentation.screen.QuestionAnswerPairsQuestionScreenPreview
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreenPreview
import cz.cvut.docta.sectionEditing.presentation.screen.CourseEditingScreenPreview
import cz.cvut.docta.sectionEditing.presentation.screen.SectionEditingScreenPreview


private val appTheme = AppTheme.Light
private const val locale: String = "en"


@Preview(device = Devices.PIXEL_7_PRO, group = "Course", locale = locale)
@Composable
private fun CoursesScreenPreviewLocal() {
    CoursesScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Course", locale = locale)
@Composable
private fun AddNewCourseScreenPreviewLocal() {
    AddNewCourseScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Section", locale = locale)
@Composable
private fun CourseSectionsScreenPreviewLocal() {
    CourseSectionsScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Lesson", locale = locale)
@Composable
private fun SectionLessonsScreenPreviewLocal() {
    SectionLessonsScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun OpenAnswerQuestionScreenPreviewLocal() {
    OpenAnswerQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun FillInBlanksQuestionScreenPreviewLocal() {
    FillInBlanksQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun AnswerOptionsQuestionScreenPreviewLocal() {
    AnswerOptionsQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun QuestionAnswerPairsQuestionScreenPreviewLocal() {
    QuestionAnswerPairsQuestionScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "CourseManagement")
@Composable
private fun CourseEditingScreenPreviewLocal() {
    CourseEditingScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "CourseManagement")
@Composable
private fun SectionEditingScreenPreviewLocal() {
    SectionEditingScreenPreview(
        appTheme = appTheme
    )
}