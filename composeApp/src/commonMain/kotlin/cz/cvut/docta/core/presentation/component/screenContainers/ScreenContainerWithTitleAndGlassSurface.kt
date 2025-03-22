package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import cz.cvut.docta.core.presentation.theme.NotoSans

@Composable
fun ScreenContainerWithTitleAndGlassSurface(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    title: String,
    glassSurfaceContent: @Composable BoxScope.() -> Unit,
    fillGlassSurface: Boolean = false,
    glassSurfaceFilledWidths: FilledWidthByScreenType = FilledWidthByScreenType(compact = .86f),
    buttonUnderGlassSurface: @Composable (() -> Unit)? = null,
    bottomButton: @Composable () -> Unit
) {
    ScreenContainer(
        screenPadding = screenPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        padding = PaddingValues(top = 8.dp, bottom = 24.dp)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = fillGlassSurface)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = title,
                style = DoctaTypography.titleLarge,
                color = DoctaColors.onSurface,
                fontFamily = NotoSans,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                    .padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                GlassSurface(
                    modifier = Modifier.weight(1f, fill = fillGlassSurface),
                    filledWidths = glassSurfaceFilledWidths,
                    content = glassSurfaceContent
                )
                buttonUnderGlassSurface?.invoke()
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        bottomButton()
        
    }
}