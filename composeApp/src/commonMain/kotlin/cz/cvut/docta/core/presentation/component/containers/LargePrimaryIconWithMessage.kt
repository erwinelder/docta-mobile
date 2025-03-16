package cz.cvut.docta.core.presentation.component.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.other.LargePrimaryIcon
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.core.presentation.theme.NotoSans
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun LargePrimaryIconWithMessage(
    title: String,
    message: String? = null,
    iconRes: DrawableResource,
    iconDescription: String,
    gradientColor: List<Color> = DoctaColors.primaryGradient,
    filledWidth: FilledWidthByScreenType? = FilledWidthByScreenType(compact = .86f)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(filledWidth?.getByType(CurrWindowType) ?: 1f)
    ) {
        LargePrimaryIcon(
            iconRes = iconRes,
            gradientColor = gradientColor,
            iconDescription = iconDescription
        )
        Text(
            text = title,
            style = DoctaTypography.titleLarge,
            color = DoctaColors.onSurface,
            fontFamily = NotoSans,
            modifier = Modifier
                .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
        )
        message?.let {
            Text(
                text = message,
                color = DoctaColors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Manrope,
                textAlign = TextAlign.Center
            )
        }
    }
}