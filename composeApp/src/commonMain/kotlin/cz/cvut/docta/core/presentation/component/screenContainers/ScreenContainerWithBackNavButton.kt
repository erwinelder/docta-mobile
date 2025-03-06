package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScreenContainerWithBackNavButton(
    onBackButtonClick: () -> Unit,
    backButtonText: String,
    backButtonIconRes: DrawableResource? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
    padding: PaddingValues = PaddingValues(top = 8.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement,
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GlassSurfaceTopBackNavButton(
                text = backButtonText,
                iconRes = backButtonIconRes,
                onClick = onBackButtonClick
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = verticalArrangement,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            content()
        }
    }
}