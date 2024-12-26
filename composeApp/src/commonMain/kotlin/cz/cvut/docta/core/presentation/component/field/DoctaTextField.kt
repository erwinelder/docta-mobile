package cz.cvut.docta.core.presentation.component.field

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.hide_icon
import docta.composeapp.generated.resources.show_icon
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoctaTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    readOnly: Boolean = false,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    fontSize: TextUnit = 22.sp,
    padding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
    cornerSize: Dp = 15.dp
) {
    val interactionSource = remember { MutableInteractionSource() }

    val textColor = DoctaColors.onSurface
    val containerColor = DoctaColors.surface

    var isPasswordVisible by remember {
        mutableStateOf(false.takeIf {
            keyboardType == KeyboardType.Password ||
            keyboardType == KeyboardType.NumberPassword
        })
    }

    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        readOnly = readOnly,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (isPasswordVisible == false) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        singleLine = true,
        textStyle = TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Light,
            fontFamily = Manrope,
            textAlign = TextAlign.Center
        ),
        modifier = modifier.animateContentSize()
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = text,
            singleLine = true,
            enabled = true,
            isError = isError,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            shape = RoundedCornerShape(cornerSize),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            contentPadding = padding,
            trailingIcon = isPasswordVisible?.let { {
                IconButton(onClick = { isPasswordVisible = !it }) {
                    Icon(
                        painter = painterResource(
                            if (it) Res.drawable.hide_icon else Res.drawable.show_icon
                        ),
                        tint = DoctaColors.onSurface,
                        contentDescription = if (it) "Hide password" else "Show password",
                        modifier = Modifier.size(26.dp)
                    )
                }
            } },
            innerTextField = {
                Box(contentAlignment = Alignment.Center) {
                    if (text.isNotBlank()) {
                        it()
                    } else {
                        Text(
                            text = placeholderText,
                            fontSize = fontSize,
                            color = DoctaColors.outline,
                            fontWeight = FontWeight.Normal,
                            fontFamily = Manrope,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        )
    }
}