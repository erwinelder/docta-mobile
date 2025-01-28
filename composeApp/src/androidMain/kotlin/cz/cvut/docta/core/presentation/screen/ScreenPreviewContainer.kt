package cz.cvut.docta.core.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.other.AppBackgroundPreview
import cz.cvut.docta.core.presentation.theme.DoctaTheme

@Composable
fun ScreenPreviewContainer(
    appTheme: AppTheme = AppTheme.Light,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this,
            isSystemInDarkTheme = appTheme == AppTheme.Dark
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AppBackgroundPreview(appTheme = appTheme)
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        }
    }
}