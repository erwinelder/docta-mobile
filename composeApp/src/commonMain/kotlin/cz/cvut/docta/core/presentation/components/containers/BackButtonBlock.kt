package cz.cvut.docta.core.presentation.components.containers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.components.buttons.BackButton
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@Composable
fun BackButtonBlock(onClick: () -> Unit, text: String = stringResource(Res.string.back)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
    ) {
        BackButton(onClick = onClick, text = text)
    }
}