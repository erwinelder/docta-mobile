package cz.cvut.docta.di

import cz.cvut.docta.core.presentation.viewmodel.NavViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    /* ---------- View Models ---------- */

    viewModel {
        NavViewModel()
    }

}