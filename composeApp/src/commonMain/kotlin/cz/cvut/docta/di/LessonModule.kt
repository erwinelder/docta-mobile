package cz.cvut.docta.di

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.local.source.lessonLocalDataSourceFactory
import cz.cvut.docta.lesson.data.remote.source.LessonRemoteDataSource
import cz.cvut.docta.lesson.data.remote.source.lessonRemoteDataSourceFactory
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.data.repository.LessonRepositoryImpl
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCaseImpl
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCaseImpl
import cz.cvut.docta.lesson.presentation.viewmodel.LessonProgressViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.LessonViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {

    /* ---------- Data Sources ---------- */

    single<LessonLocalDataSource> {
        lessonLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<LessonRemoteDataSource> {
        lessonRemoteDataSourceFactory(appRemoteDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<LessonRepository> {
        LessonRepositoryImpl(
            localSource = get(),
            remoteSource = get()
        )
    }

    /* ---------- Use Cases ---------- */

    single<GetSectionLessonsDraftsUseCase> {
        GetSectionLessonsDraftsUseCaseImpl(
            lessonRepository = get(),
            courseContext = get()
        )
    }

    single<GetSectionLessonsWithStatisticsUseCase> {
        GetSectionLessonsWithStatisticsUseCaseImpl(
            lessonRepository = get(),
            courseContext = get()
        )
    }

    /* ---------- View Models ---------- */

    viewModel {
        SectionLessonsViewModel(
            getSectionUseCase = get(),
            getSectionLessonsWithStatisticsUseCase = get()
        )
    }

    viewModel {
        LessonProgressViewModel()
    }

    viewModel {
        LessonViewModel(
            getLessonQuestionsWithAnswersUseCase = get()
        )
    }

}