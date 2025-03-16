package cz.cvut.docta.core.presentation.component.field

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.hide_icon
import docta.composeapp.generated.resources.show_icon
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LargeTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    fontSize: TextUnit = 20.sp,
    padding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    cornerSize: Dp = 16.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    readOnly: Boolean = false,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    var isPasswordVisible by remember {
        mutableStateOf(false.takeIf {
            keyboardType == KeyboardType.Password ||
            keyboardType == KeyboardType.NumberPassword
        })
    }
    val visualTransformation = if (isPasswordVisible == false) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    GlassSurface(
        cornerSize = cornerSize,
        modifier = modifier
    ) {
        TextSelectionColorsProviderWrapper {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                readOnly = readOnly,
                interactionSource = interactionSource,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                    onDone = { focusManager.clearFocus() }
                ),
                visualTransformation = visualTransformation,
                singleLine = true,
                textStyle = TextStyle(
                    color = DoctaColors.onSurface,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Manrope
                ),
                cursorBrush = Brush.linearGradient(DoctaColors.primaryGradient),
                modifier = Modifier.fillMaxWidth()
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
                        backgroundColor = Color.Transparent,
                        cursorColor = DoctaColors.primary,
                        trailingIconColor = DoctaColors.primary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    ),
                    contentPadding = padding,
                    trailingIcon = isPasswordVisible?.let { {
                        ShowHidePasswordIcon(isPasswordVisible = it) { isPasswordVisible = !it }
                    } },
                    innerTextField = {
                        if (text.isBlank()) {
                            Placeholder(text = placeholderText, fontSize = fontSize)
                        } else {
                            it()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun ShowHidePasswordIcon(
    isPasswordVisible: Boolean,
    onToggle: () -> Unit
) {
    IconButton(onClick = onToggle) {
        Icon(
            painter = painterResource(
                if (isPasswordVisible) Res.drawable.hide_icon else Res.drawable.show_icon
            ),
            tint = DoctaColors.onSurface,
            contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun Placeholder(
    text: String,
    fontSize: TextUnit
) {
    Text(
        text = text,
        fontSize = fontSize.times(0.95),
        color = DoctaColors.outline,
        fontWeight = FontWeight.Normal,
        fontFamily = Manrope
    )
}