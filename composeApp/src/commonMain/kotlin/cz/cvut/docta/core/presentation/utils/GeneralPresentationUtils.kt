package cz.cvut.docta.core.presentation.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun isKeyboardVisible(): State<Boolean> {
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return rememberUpdatedState(isKeyboardVisible)
}

@Composable
fun getImeBottomInset(): State<Dp> {
    val imeBottomInset = WindowInsets.ime.getBottom(LocalDensity.current)
    val imeBottomInsetDp = with(LocalDensity.current) { imeBottomInset.toDp() }
    return rememberUpdatedState(imeBottomInsetDp)
}