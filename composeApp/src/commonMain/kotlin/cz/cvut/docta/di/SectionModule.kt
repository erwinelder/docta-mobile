package cz.cvut.docta.di

import cz.cvut.docta.section.data.repository.SectionRepository
import cz.cvut.docta.section.data.repository.SectionRepositoryImpl
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCase
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCaseImpl
import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionUseCaseImpl
import cz.cvut.docta.section.domain.usecase.GetSectionsWithProgressUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionsWithProgressUseCaseImpl
import cz.cvut.docta.section.presentation.viewmodel.CourseSectionsViewModel
import cz.cvut.docta.sectionEditing.data.local.source.SectionDraftLocalDataSource
import cz.cvut.docta.sectionEditing.data.local.source.sectionDraftLocalDataSourceFactory
import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepository
import cz.cvut.docta.sectionEditing.data.repository.SectionDraftRepositoryImpl
import cz.cvut.docta.sectionEditing.domain.usecase.GetCourseDraftSectionsUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.GetCourseDraftSectionsUseCaseImpl
import cz.cvut.docta.sectionEditing.domain.usecase.GetSectionDraftUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.GetSectionDraftUseCaseImpl
import cz.cvut.docta.sectionEditing.domain.usecase.SaveSectionDraftUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.SaveSectionDraftUseCaseImpl
import cz.cvut.docta.sectionEditing.presentation.viewmodel.SectionDraftViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sectionModule = module {

    /* ---------- Data Sources ---------- */

    single<SectionDraftLocalDataSource> {
        sectionDraftLocalDataSourceFactory(appDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<SectionRepository> {
        SectionRepositoryImpl(userContext = get())
    }

    single<SectionDraftRepository> {
        SectionDraftRepositoryImpl(localSource = get())
    }

    /* ---------- Use Cases ---------- */

    single<GetCourseSectionsUseCase> {
        GetCourseSectionsUseCaseImpl(sectionRepository = get())
    }
    single<GetSectionsWithProgressUseCase> {
        GetSectionsWithProgressUseCaseImpl(sectionRepository = get())
    }
    single<GetSectionUseCase> {
        GetSectionUseCaseImpl(
            sectionRepository = get(),
            courseContext = get()
        )
    }

    single<GetCourseDraftSectionsUseCase> {
        GetCourseDraftSectionsUseCaseImpl(sectionDraftRepository = get())
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
            getSectionsWithProgressUseCase = get()
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