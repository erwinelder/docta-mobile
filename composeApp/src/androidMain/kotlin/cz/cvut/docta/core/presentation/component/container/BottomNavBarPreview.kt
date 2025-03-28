package cz.cvut.docta.core.presentation.component.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.navigation.BottomBarNavButton
import cz.cvut.docta.core.presentation.preview.PreviewContainer

@Preview(device = PIXEL_7_PRO)
@Composable
fun BottomNavBarPreview() {
    PreviewContainer(contentAlignment = Alignment.BottomEnd) {
        BottomNavBar(
            isVisible = true,
            anyScreenInHierarchyIsScreenProvider = { false },
            currentScreenIsScreenProvider = { false },
            onNavigateToScreen = {},
            barButtons = BottomBarNavButton.asDefaultList()
        )
    }
}