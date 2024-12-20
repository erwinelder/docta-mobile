package cz.cvut.docta.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cz.cvut.docta.core.data.local.AppDatabase
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.other.AppBackground
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    database: AppDatabase
) {
    val appTheme = AppTheme.Light

    MaterialTheme {
        Box {
            AppBackground(appTheme = appTheme)
            MainApplicationContent()
        }
    }
}