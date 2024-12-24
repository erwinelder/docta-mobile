package cz.cvut.docta.core.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.other.AppBackgroundPreview

@Composable
fun ScreenPreviewContainer(
    appTheme: AppTheme = AppTheme.Light,
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AppBackgroundPreview(appTheme = appTheme)
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                content()
            }
        }
    }
}