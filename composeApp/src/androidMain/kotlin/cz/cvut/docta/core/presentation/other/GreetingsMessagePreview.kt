package cz.cvut.docta.core.presentation.other

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.core.presentation.component.other.GreetingsMessage
import cz.cvut.docta.core.presentation.preview.PreviewContainer

@Preview(device = PIXEL_7_PRO)
@Composable
fun GreetingsMessagePreview() {
    PreviewContainer {
        GreetingsMessage()
    }
}