package cz.cvut.docta.core.presentation.component.picker

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.short_arrow_down_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun PickerButton(
    text: String,
    isExpanded: Boolean,
    selectedColor: Color,
    fontSize: TextUnit = 19.sp,
    gradientColor: List<Color> = DoctaColors.glassSurfaceGradient,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val scaleY by animateFloatAsState(targetValue = if (isExpanded) -1F else 1F)

    Button(
        onClick = onClick,
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Companion.Transparent),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier.Companion
            .bounceClickEffect(enabled = enabled)
            .border(1.dp, DoctaColors.glassSurfaceBorder, RoundedCornerShape(40))
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(40))
            .background(
                brush = Brush.Companion.linearGradient(
                    colors = gradientColor,
                    start = Offset(75f, 210f),
                    end = Offset(95f, -10f)
                )
            )
    ) {
        AnimatedContent(
            targetState = text,
            modifier = Modifier.Companion.weight(1f, fill = false)
        ) { collectionName ->
            Text(
                text = collectionName,
                color = selectedColor,
                fontSize = fontSize,
                fontFamily = Manrope,
                fontWeight = FontWeight.Companion.Normal,
                maxLines = 1,
                overflow = TextOverflow.Companion.Ellipsis
            )
        }
        Spacer(modifier = Modifier.Companion.width(8.dp))
        Icon(
            painter = painterResource(Res.drawable.short_arrow_down_icon),
            contentDescription = "expanded list picker icon",
            tint = selectedColor,
            modifier = Modifier.Companion
                .scale(scaleX = 1F, scaleY = scaleY)
                .width(20.dp)
        )
    }
}