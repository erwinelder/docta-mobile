package cz.cvut.docta.di

import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSource
import cz.cvut.docta.lesson.data.local.source.LessonLocalDataSourceImpl
import cz.cvut.docta.lesson.data.repository.LessonRepository
import cz.cvut.docta.lesson.data.repository.LessonRepositoryImpl
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsUseCaseImpl
import cz.cvut.docta.lesson.presentation.viewmodel.SectionLessonsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lessonModule = module {

    single<LessonLocalDataSource> {
        LessonLocalDataSourceImpl(dao = get())
    }

    single<LessonRepository> {
        LessonRepositoryImpl(localSource = get())
    }

    single<GetSectionLessonsUseCase> {
        GetSectionLessonsUseCaseImpl(lessonRepository = get())
    }

    viewModel {
        SectionLessonsViewModel(
            getSectionUseCase = get(),
            getSectionLessonsUseCase = get()
        )
    }
}