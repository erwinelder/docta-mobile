package cz.cvut.docta.core.presentation.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun SetBackHandler(enabled: Boolean = true, onBack: () -> Unit = {})