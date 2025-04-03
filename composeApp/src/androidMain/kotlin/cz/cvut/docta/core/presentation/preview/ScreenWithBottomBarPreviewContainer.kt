package cz.cvut.docta.core.presentation.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.component.container.BottomNavBar
import cz.cvut.docta.core.presentation.other.AppBackgroundPreview
import cz.cvut.docta.core.presentation.theme.DoctaTheme
import cz.cvut.docta.core.presentation.utils.isScreen
import cz.cvut.docta.course.presentation.navigation.CourseScreens

@Composable
fun ScreenWithBottomBarPreviewContainer(
    appTheme: AppTheme = AppTheme.Light,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this,
            isSystemInDarkTheme = appTheme == AppTheme.Dark
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AppBackgroundPreview(appTheme = appTheme)
                Scaffold(
                    modifier = Modifier
                        .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()),
                    bottomBar = {
                        BottomNavBar(
                            isVisible = true,
                            padding = PaddingValues(
                                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                            ),
                            anyScreenInHierarchyIsScreenProvider = { it.isScreen(CourseScreens.Courses) },
                            currentScreenIsScreenProvider = { it.isScreen(CourseScreens.Courses) },
                            onNavigateToScreen = {}
                        )
                    },
                    backgroundColor = Color.Transparent
                ) { screenPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(screenPadding)
                    ) {
                        content()
                    }
                }
            }
        }
    }
}