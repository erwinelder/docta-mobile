package cz.cvut.docta.course_edit.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.course_edit.data.model.CourseEditingEntity
import cz.cvut.docta.course_edit.presentation.viewmodel.CourseEditingViewModel
import org.koin.compose.getKoin

@Composable
fun CourseEditingScreen(courseCode: String, onNavigateBack: () -> Unit) {
    val viewModel: CourseEditingViewModel = getKoin().get()
    val courseEditingState by viewModel.courseEditingState.collectAsState()

    val name = remember { mutableStateOf(courseEditingState?.name ?: "") }
    val locale = remember { mutableStateOf(courseEditingState?.locale ?: "") }

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GlassSurfaceTopBackNavButton(
                text = "Edit Course",
                onClick = onNavigateBack
            )
        }
        LazyColumn(
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items = listOf(Unit)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("Course Name") }
                    )
                    TextField(
                        value = locale.value,
                        onValueChange = { locale.value = it },
                        label = { Text("Locale") }
                    )
                    Button(
                        onClick = {
                            viewModel.saveCourseEditing(
                                CourseEditingEntity(
                                    code = courseCode,
                                    name = name.value,
                                    locale = locale.value
                                )
                            )
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}