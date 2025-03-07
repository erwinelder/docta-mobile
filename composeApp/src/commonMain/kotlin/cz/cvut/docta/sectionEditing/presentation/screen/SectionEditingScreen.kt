package cz.cvut.docta.sectionEditing.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.core.presentation.component.field.SmallTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun SectionEditingScreen(
    onNavigateBack: () -> Unit,
    sectionName: String,
    onNameChange: (String) -> Unit,
    onSaveButtonClick: () -> Unit
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {
        GlassSurfaceTopBackNavButton(
            text = stringResource(SharedRes.strings.edit_section),
            onClick = onNavigateBack
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            SmallTextField(
                text = sectionName,
                onValueChange = onNameChange,
            )
        }
        PrimaryButton(
            text = stringResource(SharedRes.strings.save),
            onClick = onSaveButtonClick
        )
    }
}