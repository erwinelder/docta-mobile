package cz.cvut.docta.core.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.mapper.toResultState
import cz.cvut.docta.auth.presentation.model.AuthSuccessScreenType
import cz.cvut.docta.auth.presentation.screen.AuthSuccessScreenPreview
import cz.cvut.docta.auth.presentation.screen.EmailVerificationScreenPreview
import cz.cvut.docta.auth.presentation.screen.SignInScreenPreview
import cz.cvut.docta.auth.presentation.screen.SignUpScreenPreview
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.course.presentation.screen.AddNewCourseScreenPreview
import cz.cvut.docta.course.presentation.screen.CoursesScreenPreview
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.lesson.presentation.screen.SectionLessonsScreenPreview
import cz.cvut.docta.lessonSession.presentation.screen.AnswerOptionsQuestionScreenPreview
import cz.cvut.docta.lessonSession.presentation.screen.FillInBlanksQuestionScreenPreview
import cz.cvut.docta.lessonSession.presentation.screen.OpenAnswerQuestionScreenPreview
import cz.cvut.docta.lessonSession.presentation.screen.QuestionAnswerPairsQuestionScreenPreview
import cz.cvut.docta.section.presentation.screen.CourseSectionsScreenPreview
import cz.cvut.docta.sectionEditing.presentation.screen.CourseEditingScreenPreview
import cz.cvut.docta.sectionEditing.presentation.screen.SectionEditingScreenPreview


private val appTheme = AppTheme.Light
private const val locale: String = "en"


@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun SignInScreenPreview_() {
    SignInScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun SignUpScreenPreview_() {
    SignUpScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun EmailVerificationScreenPromptedPreview() {
    EmailVerificationScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun EmailVerificationScreenNotVerifiedPreview() {
    EmailVerificationScreenPreview(
        appTheme = appTheme,
        requestState = RequestState.Result(
            resultState = AuthError.EmailNotVerifiedYet.toResultState()
        )
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Auth", locale = locale)
@Composable
private fun AuthSuccessScreenPreview_() {
    AuthSuccessScreenPreview(
        appTheme = appTheme,
        screenType = AuthSuccessScreenType.SignUp
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Course", locale = locale)
@Composable
private fun CoursesScreenPreview_() {
    CoursesScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Course", locale = locale)
@Composable
private fun AddNewCourseScreenPreview_() {
    AddNewCourseScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Section", locale = locale)
@Composable
private fun CourseSectionsScreenPreview_() {
    CourseSectionsScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Lesson", locale = locale)
@Composable
private fun SectionLessonsScreenPreview_() {
    SectionLessonsScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun OpenAnswerQuestionScreenPreview_() {
    OpenAnswerQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun FillInBlanksQuestionScreenPreview_() {
    FillInBlanksQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun AnswerOptionsQuestionScreenPreview_() {
    AnswerOptionsQuestionScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "Question", locale = locale)
@Composable
private fun QuestionAnswerPairsQuestionScreenPreview_() {
    QuestionAnswerPairsQuestionScreenPreview(
        appTheme = appTheme
    )
}


@Preview(device = Devices.PIXEL_7_PRO, group = "CourseManagement")
@Composable
private fun CourseEditingScreenPreview_() {
    CourseEditingScreenPreview(
        appTheme = appTheme
    )
}

@Preview(device = Devices.PIXEL_7_PRO, group = "CourseManagement")
@Composable
private fun SectionEditingScreenPreview_() {
    SectionEditingScreenPreview(
        appTheme = appTheme
    )
}