package cz.cvut.docta.answer.presentation.component.field

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.containers.GlassSurface
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnswerTextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    GlassSurface(
        filledWidths = FilledWidthByScreenType(.86f)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            visualTransformation = VisualTransformation.None,
            textStyle = TextStyle(
                color = DoctaColors.onSurface,
                fontSize = 17.sp,
                fontWeight = FontWeight.W500,
                fontFamily = Manrope
            ),
            modifier = Modifier.height(280.dp)
            ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                singleLine = false,
                enabled = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                ),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                innerTextField = {
                    if (text.isNotBlank()) {
                        it()
                    } else {
                        Text(
                            text = "Type your answer here…",
                            fontSize = 17.sp,
                            color = DoctaColors.outline,
                            fontWeight = FontWeight.W500,
                            fontFamily = Manrope
                        )
                    }
                }
            )
        }
    }
}