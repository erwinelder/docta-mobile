package cz.cvut.docta.core.presentation.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.other.AppBackgroundPreview
import cz.cvut.docta.core.presentation.theme.DoctaTheme

@Composable
fun PreviewWrapper(
    appTheme: AppTheme = AppTheme.Light,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        DoctaTheme(
            boxWithConstraintsScope = this,
            isSystemInDarkTheme = appTheme == AppTheme.Dark
        ) {
            Box {
                AppBackgroundPreview(appTheme = appTheme)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier.padding(vertical = 32.dp)
                ) {
                    content()
                }
            }
        }
    }
}