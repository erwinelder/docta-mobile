package cz.cvut.docta.core.presentation.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.short_arrow_left_icon
import docta.composeapp.generated.resources.short_arrow_right_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavigationTextArrowButton(
    text: String,
    showLeftArrow: Boolean = false,
    fontSize: TextUnit = 18.sp,
    iconSize: Dp = 16.dp,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.bounceClickEffect()
    ) {
        if (showLeftArrow) {
            Icon(
                painter = painterResource(Res.drawable.short_arrow_left_icon),
                contentDescription = "short left arrow",
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = text,
            color = DoctaColors.primary,
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            fontFamily = Manrope
        )
        if (!showLeftArrow) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(Res.drawable.short_arrow_right_icon),
                contentDescription = "short right arrow",
                modifier = Modifier.size(iconSize)
            )
        }
    }
}