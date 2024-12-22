package cz.cvut.docta

import androidx.compose.ui.window.ComposeUIViewController
import cz.cvut.docta.di.initKoin
import cz.cvut.docta.core.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}