package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.core.presentation.utils.add

@Composable
fun ScreenContainerWithTitle(
    title: String,
    titleAlign: TextAlign = TextAlign.Left,
    titleStyle: TextStyle = LocalTextStyle.current,
    titleModifier: Modifier = Modifier,
    screenPadding: PaddingValues = PaddingValues(0.dp),
    padding: PaddingValues = PaddingValues(vertical = 24.dp, horizontal = 24.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        modifier = Modifier
            .padding(padding.add(screenPadding))
            .fillMaxSize()
            .then(modifier)
    ) {

        Text(
            text = title,
            fontFamily = Manrope,
            textAlign = titleAlign,
            style = titleStyle,
            modifier = titleModifier
        )

        content()
    }
}