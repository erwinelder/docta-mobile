package cz.cvut.docta.core.presentation.component.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.modifier.bounceClickEffect
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@Composable
fun <T> FilterBar(
    buttonStates: List<T>,
    onGetButtonText: @Composable (T) -> String,
    onIsButtonActive: (T) -> Boolean,
    onButtonClick: (T) -> Unit
) {
    val scrollState = rememberScrollState()

    val getGradientColors = @Composable { buttonState: T ->
        if (onIsButtonActive(buttonState)) DoctaColors.primaryGradient else
            DoctaColors.glassSurfaceGradient
    }
    val getButtonTextColor = @Composable { buttonState: T ->
        if (onIsButtonActive(buttonState)) DoctaColors.onPrimary else DoctaColors.onSurface
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.width(8.dp))

        buttonStates.forEach { buttonState ->
            TextButton(
                onClick = {
                    onButtonClick(buttonState)
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Transparent
                ),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(50),
                modifier = Modifier.bounceClickEffect()
            ) {
                Text(
                    text = onGetButtonText(buttonState),
                    color = getButtonTextColor(buttonState),
                    fontSize = 16.sp,
                    fontFamily = Manrope,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = getGradientColors(buttonState),
                                start = Offset(75f, 210f),
                                end = Offset(95f, -10f)
                            )
                        )
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))
    }
}