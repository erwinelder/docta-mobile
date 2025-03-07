package cz.cvut.docta.di

import cz.cvut.docta.section.data.local.source.SectionLocalDataSource
import cz.cvut.docta.section.data.local.source.sectionLocalDataSourceFactory
import cz.cvut.docta.section.data.remote.source.SectionRemoteDataSource
import cz.cvut.docta.section.data.remote.source.sectionRemoteDataSourceFactory
import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.data.repository.SectionRepositoryImpl
import cz.cvut.docta.section.domain.usecase.GetCourseDraftSectionsUseCase
import cz.cvut.docta.section.domain.usecase.GetCourseDraftSectionsUseCaseImpl
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCase
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCaseImpl
import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionUseCaseImpl
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import cz.cvut.docta.sectionEditing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.sectionEditing.data.local.source.sectionDraftLocalDataSourceFactory
import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepository
import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepositoryImpl
import cz.cvut.docta.sectionEditing.domain.usecase.GetSectionDraftUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.GetSectionDraftUseCaseImpl
import cz.cvut.docta.sectionEditing.domain.usecase.SaveSectionDraftUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.SaveSectionDraftUseCaseImpl
import cz.cvut.docta.sectionEditing.presentation.viewmodel.SectionDraftViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sectionModule = module {

    /* ---------- Data Sources ---------- */

    single<SectionLocalDataSource> {
        sectionLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<SectionRemoteDataSource> {
        sectionRemoteDataSourceFactory(appRemoteDatabase = get())
    }

    single<SectionDraftLocalDataSource> {
        sectionDraftLocalDataSourceFactory(appLocalDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<SectionRepository> {
        SectionRepositoryImpl(
            localSource = get(),
            remoteSource = get()
        )
    }

    single<SectionDraftRepository> {
        SectionDraftRepositoryImpl(localSource = get())
    }

    /* ---------- Use Cases ---------- */

    single<GetCourseSectionsUseCase> {
        GetCourseSectionsUseCaseImpl(sectionRepository = get())
    }
    single<GetSectionUseCase> {
        GetSectionUseCaseImpl(
            sectionRepository = get(),
            courseContext = get()
        )
    }

    single<GetCourseDraftSectionsUseCase> {
        GetCourseDraftSectionsUseCaseImpl(sectionRepository = get())
    }

    single<GetSectionDraftUseCase> {
        GetSectionDraftUseCaseImpl(
            sectionRepository = get(),
            sectionDraftRepository = get(),
            courseContext = get()
        )
    }
    single<SaveSectionDraftUseCase> {
        SaveSectionDraftUseCaseImpl(repository = get())
    }

    /* ---------- View Models ---------- */

    viewModel {
        CourseSectionsViewModel(
            getCourseUseCase = get(),
            getCourseSectionsUseCase = get()
        )
    }

    viewModel {
        SectionDraftViewModel(
            getSectionDraftUseCase = get(),
            saveSectionDraftUseCase = get(),
            getSectionLessonsDraftsUseCase = get()
        )
    }
}