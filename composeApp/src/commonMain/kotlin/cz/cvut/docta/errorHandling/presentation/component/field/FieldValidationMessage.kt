package cz.cvut.docta.errorHandling.presentation.component.field

import androidx.compose.animation.AnimatedContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.errorHandling.presentation.model.ValidationUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun FieldValidationMessage(
    state: ValidationUiState,
    fontSize: TextUnit = 15.sp
) {
    AnimatedContent(targetState = state) { targetState ->
        Text(
            text = "* " + stringResource(targetState.messageRes),
            fontSize = fontSize,
            color = if (targetState.isValid) DoctaColors.success else DoctaColors.error,
            fontWeight = FontWeight.W400,
            fontFamily = Manrope,
            textAlign = TextAlign.Center
        )
    }
}