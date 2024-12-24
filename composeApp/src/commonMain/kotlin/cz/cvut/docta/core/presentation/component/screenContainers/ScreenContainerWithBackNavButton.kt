package cz.cvut.docta.core.presentation.component.screenContainers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.component.containers.BackButtonBlock

@Composable
fun ScreenContainerWithBackNavButton(
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    onBackButtonClick: (() -> Unit)? = null,
    padding: PaddingValues = PaddingValues(vertical = 24.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement,
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        onBackButtonClick?.let { BackButtonBlock(it) }
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