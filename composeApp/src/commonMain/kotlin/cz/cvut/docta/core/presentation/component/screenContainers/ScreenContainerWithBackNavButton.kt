package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.buttons.GlassSurfaceTopBackNavButton
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ScreenContainerWithBackNavButton(
    onBackButtonClick: () -> Unit,
    backButtonText: String,
    backButtonIconRes: DrawableResource? = null,
    gap: Dp = 24.dp,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp),
    contentFilledWith: FilledWidthByScreenType? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(gap),
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
    ) {
        GlassSurfaceTopBackNavButton(
            text = backButtonText,
            iconRes = backButtonIconRes,
            onClick = onBackButtonClick
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