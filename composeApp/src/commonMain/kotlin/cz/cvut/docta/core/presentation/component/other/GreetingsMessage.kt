package cz.cvut.docta.core.presentation.component.other

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.core.utils.getCurrentLocalDateTime
import cz.cvut.docta.core.utils.getGreetingsWidgetTitleRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun GreetingsMessage(username: String) {
    val currentLocalDateTime = getCurrentLocalDateTime()
    val greetingsTitleRes by remember(currentLocalDateTime.hour) {
        derivedStateOf {
            currentLocalDateTime.hour.getGreetingsWidgetTitleRes()
        }
    }

    GreetingsMessageContent(message = stringResource(greetingsTitleRes, username))
}

@Composable
fun GreetingsMessageContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = message,
            color = DoctaColors.onSurface,
            fontFamily = Manrope,
            style = DoctaTypography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}