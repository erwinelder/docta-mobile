package cz.cvut.docta.di

import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.data.repository.LessonRepositoryImpl
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCaseImpl
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCaseImpl
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonProgressViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonResultsViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.lesson.LessonViewModel
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import cz.cvut.docta.lessonSession.domain.usecase.DeleteLessonSessionUseCase
import cz.cvut.docta.lessonSession.domain.usecase.DeleteLessonSessionUseCaseImpl
import cz.cvut.docta.lessonSession.domain.usecase.FinishLessonSessionUseCase
import cz.cvut.docta.lessonSession.domain.usecase.FinishLessonSessionUseCaseImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {

    /* ---------- Repositories ---------- */

    single<LessonRepository> {
        LessonRepositoryImpl(userContext = get())
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

    single<FinishLessonSessionUseCase> {
        FinishLessonSessionUseCaseImpl(lessonSessionRepository = get())
    }

    single<DeleteLessonSessionUseCase> {
        DeleteLessonSessionUseCaseImpl(lessonSessionRepository = get())
    }

    /* ---------- View Models ---------- */

    viewModel { parameters ->
        SectionLessonsViewModel(
            sectionId = parameters.get<Int>(),
            getSectionUseCase = get(),
            getSectionLessonsWithStatisticsUseCase = get()
        )
    }

    viewModel {
        LessonProgressViewModel()
    }

    viewModel {
        LessonViewModel(getLessonQuestionsWithAnswersUseCase = get())
    }

    viewModel {
        LessonResultsViewModel(
            finishLessonSessionUseCase = get(),
            deleteLessonSessionUseCase = get()
        )
    }

}