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
import cz.cvut.docta.section_draft.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.section_draft.data.local.source.sectionDraftLocalDataSourceFactory
import cz.cvut.docta.section_draft.data.repository.SectionDraftRepository
import cz.cvut.docta.section_draft.data.repository.SectionDraftRepositoryImpl
import cz.cvut.docta.section_draft.domain.usecase.GetSectionDraftUseCase
import cz.cvut.docta.section_draft.domain.usecase.GetSectionDraftUseCaseImpl
import cz.cvut.docta.section_draft.domain.usecase.SaveSectionDraftUseCase
import cz.cvut.docta.section_draft.domain.usecase.SaveSectionDraftUseCaseImpl
import cz.cvut.docta.section_draft.presentation.viewmodel.SectionDraftViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sectionModule = module {

    single<SectionLocalDataSource> {
        sectionLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<SectionDraftLocalDataSource> {
        sectionDraftLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<SectionRepository> {
        SectionRepositoryImpl(localSource = get())
    }
    single<SectionDraftRepository> {
        SectionDraftRepositoryImpl(localSource = get())
    }

    single<GetCourseSectionsUseCase> {
        GetCourseSectionsUseCaseImpl(sectionRepository = get())
    }
    single<GetSectionUseCase> {
        GetSectionUseCaseImpl(sectionRepository = get())
    }

    single<GetSectionDraftUseCase> {
        GetSectionDraftUseCaseImpl(
            sectionRepository = get(),
            sectionDraftRepository = get()
        )
    }
    single<SaveSectionDraftUseCase> {
        SaveSectionDraftUseCaseImpl(repository = get())
    }

    viewModel {
        CourseSectionsViewModel(
            getCourseUseCase = get(),
            getCourseSectionsUseCase = get()
        )
    }

    viewModel {
        SectionDraftViewModel(
            getSectionDraftUseCase = get(),
            saveSectionDraftUseCase = get()
        )
    }
}