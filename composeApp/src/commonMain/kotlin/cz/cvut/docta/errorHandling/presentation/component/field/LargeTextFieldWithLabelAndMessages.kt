package cz.cvut.docta.errorHandling.presentation.component.field

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.field.SmallTextField
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Composable
fun LargeTextFieldWithLabelAndMessages(
    state: ValidatedFieldUiState,
    onValueChange: (String) -> Unit,
    placeholderText: String = "",
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    fontSize: TextUnit = 19.sp,
    cornerSize: Dp = 15.dp,
    labelText: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    onDoneKeyboardAction: () -> Unit = {},
    onGoKeyboardAction: () -> Unit = {}
) {
    LabelWithMessagesFieldWrapper(state = state, labelText = labelText) {
        SmallTextField(
            text = state.fieldText,
            placeholderText = placeholderText,
            onValueChange = onValueChange,
            isError = isError,
            keyboardType = keyboardType,
            fontSize = fontSize,
            cornerSize = cornerSize,
            imeAction = imeAction,
            onDoneKeyboardAction = onDoneKeyboardAction,
            onGoKeyboardAction = onGoKeyboardAction
        )
    }
}