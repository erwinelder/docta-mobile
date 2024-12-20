package cz.cvut.docta

import androidx.compose.ui.window.ComposeUIViewController
import cz.cvut.docta.core.data.local.getDatabaseBuilder
import cz.cvut.docta.core.data.local.getRoomDatabase
import cz.cvut.docta.core.di.initKoin
import cz.cvut.docta.core.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}