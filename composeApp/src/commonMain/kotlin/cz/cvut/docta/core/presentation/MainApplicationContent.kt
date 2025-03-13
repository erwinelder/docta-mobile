package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.cvut.docta.achievement.presentation.AchievementsScreen
import cz.cvut.docta.achievement.presentation.viewmodel.AchievementsViewModel
import cz.cvut.docta.core.presentation.navigation.MainScreens
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import cz.cvut.docta.course.presentation.navigation.courseNavigationGraph
import cz.cvut.docta.courseEditing.presentation.navigation.courseManagementNavigationGraph
import cz.cvut.docta.lesson.presentation.component.LessonProgressBar
import cz.cvut.docta.lesson.presentation.navigation.lessonNavigationGraph
import cz.cvut.docta.lesson.presentation.viewmodel.LessonProgressViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainApplicationContent(
    navController: NavHostController = rememberNavController()
) {
    val navViewModel = koinViewModel<NavViewModel>()

    val lessonProgressViewModel = koinViewModel<LessonProgressViewModel>()
    val lessonProgression by lessonProgressViewModel.progression.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            LessonProgressBar(progression = lessonProgression)
        },
        backgroundColor = Color.Transparent
    ) { screenPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreens.CoursesGraph
        ) {
            courseNavigationGraph(
                navController = navController,
                navViewModel = navViewModel
            )
            lessonNavigationGraph(
                navController = navController,
                navViewModel = navViewModel,
                screenPadding = screenPadding,
                lessonProgressViewModel = lessonProgressViewModel
            )
            courseManagementNavigationGraph(
                navController = navController,
                navViewModel = navViewModel
            )
            composable<MainScreens.Achievements> {
                val viewModel = koinViewModel<AchievementsViewModel>()

                val achievements by viewModel.achievements.collectAsStateWithLifecycle()

                AchievementsScreen(achievements = achievements)
            }
        }
    }
}