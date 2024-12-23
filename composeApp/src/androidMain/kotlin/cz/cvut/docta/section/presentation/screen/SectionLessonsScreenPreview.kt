package cz.cvut.docta.section.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.screen.ScreenPreviewContainer

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun SectionLessonsScreenPreview() {
    ScreenPreviewContainer {
        SectionLessonsScreen()
    }
}