package cz.cvut.docta.errorHandling.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.close_icon

@Composable
fun LoadingStateComponent(
    message: String,
    onCancel: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = message,
            color = DoctaColors.outline,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            fontFamily = Manrope
        )
        SmallSecondaryButton(
            text = stringResource(SharedRes.strings.cancel),
            iconRes = Res.drawable.close_icon,
            onClick = onCancel
        )
    }
}