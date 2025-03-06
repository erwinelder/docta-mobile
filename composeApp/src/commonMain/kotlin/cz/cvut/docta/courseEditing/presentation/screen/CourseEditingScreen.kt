package cz.cvut.docta.courseEditing.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceNavigationButton
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.field.DoctaTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.section.domain.model.Section
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.edit_course
import docta.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
fun CourseEditingScreen(
    onNavigateBack: () -> Unit,
    courseName: String,
    onNameChange: (String) -> Unit,
    courseLocale: String,
    onLocaleChange: (String) -> Unit,
    onSaveButtonClick: () -> Unit,
    sections: List<Section>,
    onSectionClick: (Long) -> Unit,
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        GlassSurfaceTopBackNavButton(
            text = stringResource(Res.string.edit_course),
            onClick = onNavigateBack
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                .weight(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DoctaTextField(
                    text = courseName,
                    onValueChange = onNameChange
                )
                DoctaTextField(
                    text = courseLocale,
                    onValueChange = onLocaleChange
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = sections) { section ->
                    GlassSurfaceNavigationButton(
                        text = section.name,
                        padding = PaddingValues(
                            start = 24.dp, end = 16.dp, top = 16.dp, bottom = 16.dp
                        ),
                        cornerSize = 18.dp,
                        onClick = {
                            onSectionClick(section.id)
                        }
                    )
                }
            }
        }
        PrimaryButton(
            text = stringResource(Res.string.save),
            onClick = onSaveButtonClick
        )
    }
}