package cz.cvut.docta.di

import cz.cvut.docta.core.data.preferences.SecureStorage
import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    /* ---------- Preferences ---------- */

    single {
        SecureStorage(settings = get())
    }

    /* ---------- View Models ---------- */

    viewModel {
        NavViewModel()
    }

}