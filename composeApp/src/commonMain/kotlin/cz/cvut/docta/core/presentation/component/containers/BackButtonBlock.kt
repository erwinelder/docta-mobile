package cz.cvut.docta.core.presentation.component.containers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.BackButton
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun BackButtonBlock(onClick: () -> Unit, text: String = stringResource(SharedRes.strings.back)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
    ) {
        BackButton(onClick = onClick, text = text)
    }
}