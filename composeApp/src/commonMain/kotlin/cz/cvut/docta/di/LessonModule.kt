package cz.cvut.docta.di

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