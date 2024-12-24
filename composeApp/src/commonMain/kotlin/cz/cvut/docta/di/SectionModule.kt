package cz.cvut.docta.di

import cz.cvut.docta.section.data.local.source.SectionLocalDataSource
import cz.cvut.docta.section.data.local.source.sectionLocalDataSourceFactory
import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.data.repository.SectionRepositoryImpl
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCase
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCaseImpl
import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionUseCaseImpl
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sectionModule = module {

    single<SectionLocalDataSource> {
        sectionLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<SectionRepository> {
        SectionRepositoryImpl(localSource = get())
    }

    single< GetCourseSectionsUseCase> {
        GetCourseSectionsUseCaseImpl(sectionRepository = get())
    }
    single<GetSectionUseCase> {
        GetSectionUseCaseImpl(sectionRepository = get())
    }

    viewModel {
        CourseSectionsViewModel(
            getCourseUseCase = get(),
            getCourseSectionsUseCase = get()
        )
    }

}