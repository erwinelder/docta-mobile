package cz.cvut.docta.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.course.presentation.screen.CourseSectionsScreen
import cz.cvut.docta.course.presentation.screen.CoursesScreen
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.section.presentation.screen.SectionLessonsScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreens.Courses
    ) {
        composable<MainScreens.Courses> {
            val coursesViewModel = koinViewModel<CoursesViewModel>()
            val courseList by coursesViewModel.courseList.collectAsStateWithLifecycle()

            CoursesScreen(
                courseList = courseList,
                onCourseClick = { course ->
                    navController.navigate(MainScreens.CourseSections(courseCode = course.code))
                }
            )
        }
        composable<MainScreens.CourseSections> { backStack ->
            val courseCode = backStack.toRoute<MainScreens.CourseSections>().courseCode

            // TODO-COURSE-SECTIONS: collect course sections from relative view model

            CourseSectionsScreen(
                onBackButtonClick = navController::popBackStack,
                courseSectionList = emptyList(), // TODO-COURSE-SECTIONS: pass course sections,
                onSectionClick = { /* TODO: navigation to the specific section screen */ }
            )
        }
        composable<MainScreens.SectionLessons> { backStack ->
            val sectionId = backStack.toRoute<MainScreens.SectionLessons>().sectionId

            // TODO-SECTION-LESSONS
            val sectionName: String = "Section name"
            val activeDifficulty: LessonDifficulty? = LessonDifficulty.Easy
            val onDifficultyChange: (LessonDifficulty) -> Unit = {}
            val activeType: LessonFilterType? = null
            val onTypeSelect: (LessonFilterType) -> Unit = {}
            val lessonList: List<Lesson> = listOf()

            SectionLessonsScreen(
                sectionName = sectionName,
                activeDifficulty = activeDifficulty,
                onDifficultyChange = onDifficultyChange,
                activeType = activeType,
                onTypeSelect = onTypeSelect,
                lessonList = lessonList
            )
        }
    }
}