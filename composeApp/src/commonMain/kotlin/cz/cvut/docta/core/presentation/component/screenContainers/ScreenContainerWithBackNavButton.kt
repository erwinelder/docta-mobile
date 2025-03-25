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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.utils.add
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScreenContainerWithBackNavButton(
    onNavigateBack: () -> Unit,
    backButtonText: String,
    backButtonIconRes: DrawableResource? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
    screenPadding: PaddingValues = PaddingValues(0.dp),
    padding: PaddingValues = PaddingValues(vertical = 8.dp),
    gap: Dp = 24.dp,
    contentFilledWith: FilledWidthByScreenType? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(gap),
        modifier = Modifier
            .padding(padding.add(screenPadding))
            .fillMaxSize()
    ) {
        GlassSurfaceTopBackNavButton(
            text = backButtonText,
            iconRes = backButtonIconRes,
            onClick = onNavigateBack
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = verticalArrangement,
            modifier = Modifier
                .fillMaxWidth(contentFilledWith?.getByType(CurrWindowType) ?: 1f)
                .weight(1f)
        ) {
            content()
        }
    }
}