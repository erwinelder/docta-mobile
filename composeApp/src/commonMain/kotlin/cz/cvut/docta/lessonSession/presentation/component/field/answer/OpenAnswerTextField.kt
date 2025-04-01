package cz.cvut.docta.lessonSession.presentation.component.field.answer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.field.TextSelectionColorsProviderWrapper
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OpenAnswerTextField(
    text: String,
    onValueChange: (String) -> Unit,
    checkResult: AnswerCheckResult.Open?,
    readOnly: Boolean,
    focusManager: FocusManager = LocalFocusManager.current,
    focusRequester: FocusRequester? = null,
    onFocusChange: (FocusState) -> Unit = {}
) {
    val scrollState = rememberScrollState()

    val textColor by animateColorAsState(
        targetValue = when {
            checkResult == null -> DoctaColors.onSurface
            checkResult.isCorrect -> DoctaColors.successGlass
            else -> DoctaColors.errorGlass
        }
    )

    GlassSurface(
        filledWidths = FilledWidthByScreenType(.86f)
    ) {
        TextSelectionColorsProviderWrapper {
            Column(
                modifier = Modifier
                    .clickable { focusManager.moveFocus(FocusDirection.Next) }
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .height(280.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {

                TextField(
                    value = text,
                    onValueChange = onValueChange,
                    readOnly = readOnly,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Manrope
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = textColor,
                        cursorColor = DoctaColors.primary,
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(SharedRes.strings.type_your_answer_here),
                            fontSize = 17.sp,
                            color = DoctaColors.outline,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Manrope
                        )
                    },
                    modifier = Modifier
                        .run {
                            focusRequester?.let {
                                focusRequester(it).onFocusChanged(onFocusChange)
                            } ?: this
                        }
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                )

                AnimatedVisibility(
                    visible = checkResult?.takeIfIncorrect() != null,
                    enter = slideInVertically() + fadeIn(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    checkResult?.takeIfIncorrect()?.let {
                        Text(
                            text = it.answer,
                            color = DoctaColors.successGlass,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Manrope,
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                }

            }
        }
    }
}