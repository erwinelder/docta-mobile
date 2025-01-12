package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cz.cvut.docta.core.domain.usecase.SaveOMOCourseToDatabaseUseCase
import cz.cvut.docta.core.presentation.component.other.AppBackground
import cz.cvut.docta.core.presentation.theme.DoctaTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    saveTestCoursesToRemoteDatabase()

    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this
        ) {
            Box {
                AppBackground()
                MainApplicationContent()
            }
        }
    }
}

@Composable
private fun saveTestCoursesToRemoteDatabase() {
    val coroutineScope = rememberCoroutineScope()

    val omoCourseUseCase = koinInject<SaveOMOCourseToDatabaseUseCase>()

    coroutineScope.launch {
        omoCourseUseCase.execute()
    }
}