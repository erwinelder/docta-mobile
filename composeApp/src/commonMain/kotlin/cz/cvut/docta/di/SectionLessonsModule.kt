package cz.cvut.docta.di

import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsUseCase
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sectionLessonsModule = module {

    single<GetSectionLessonsUseCase> {
        // TODO
    }

    viewModel {
        SectionLessonsViewModel(
            getSectionUseCase = get(),
            getSectionLessonsUseCase = get()
        )
    }

}