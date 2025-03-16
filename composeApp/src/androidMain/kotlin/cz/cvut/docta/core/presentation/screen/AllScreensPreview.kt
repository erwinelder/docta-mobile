package cz.cvut.docta.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.presentation.model.AuthSuccessScreenType
import cz.cvut.docta.auth.presentation.model.EmailVerificationState
import cz.cvut.docta.auth.presentation.screen.AuthSuccessScreenPreview
import cz.cvut.docta.auth.presentation.screen.EmailVerificationScreenPreview
import cz.cvut.docta.auth.presentation.screen.SignInScreenPreview
import cz.cvut.docta.auth.presentation.screen.SignUpScreenPreview
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


@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun SignInScreenPreviewLocal() {
    SignInScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun SignUpScreenPreviewLocal() {
    SignUpScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun EmailVerificationScreenPreviewLocal() {
    EmailVerificationScreenPreview(
        appTheme = appTheme,
        emailVerificationState = EmailVerificationState.Prompted
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun AuthSuccessScreenPreviewLocal() {
    AuthSuccessScreenPreview(
        appTheme = appTheme,
        screenType = AuthSuccessScreenType.SignUp
    )
}


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