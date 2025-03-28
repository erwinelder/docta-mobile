package cz.cvut.docta.core.presentation.component.picker

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.preview.PreviewContainer

@Preview(device = PIXEL_7_PRO)
@Composable
fun ListPickerPreview() {
    PreviewContainer {
        BubblePicker(
            items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"),
            selectedItem = "Item 1",
            onItemSelect = {}
        )
    }
}