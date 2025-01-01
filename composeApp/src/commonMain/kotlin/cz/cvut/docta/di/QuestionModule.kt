package cz.cvut.docta.di

import cz.cvut.docta.lesson.domain.usecase.GetLessonQuestionsQueryOptionsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetLessonQuestionsQueryOptionsUseCaseImpl
import cz.cvut.docta.lesson.presentation.viewmodel.LessonQuestionsViewModel
import cz.cvut.docta.question.data.local.source.QuestionLocalDataSource
import cz.cvut.docta.question.data.local.source.questionLocalDataSourceFactory
import cz.cvut.docta.question.data.repository.QuestionRepository
import cz.cvut.docta.question.data.repository.QuestionRepositoryImpl
import cz.cvut.docta.question.domain.usecase.GetDefaultLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetDefaultLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.question.domain.usecase.GetStepByStepLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetStepByStepLessonQuestionsWithAnswersUseCaseImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val questionModule = module {

    single<QuestionLocalDataSource> {
        questionLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<QuestionRepository> {
        QuestionRepositoryImpl(
            questionLocalDataSource = get()
        )
    }

    single<GetLessonQuestionsQueryOptionsUseCase> {
        GetLessonQuestionsQueryOptionsUseCaseImpl(
            sectionRepository = get(),
            lessonRepository = get()
        )
    }
    single<GetDefaultLessonQuestionsWithAnswersUseCase> {
        GetDefaultLessonQuestionsWithAnswersUseCaseImpl(
            questionRepository = get(),
            getOpenAnswerQuestionWithAnswersUseCase = get(),
            getFillInBlanksQuestionWithAnswersUseCase = get(),
            getAnswerOptionsQuestionWithAnswerUseCase = get(),
            getQuestionAnswerPairsQuestionWithAnswersUseCase = get()
        )
    }
    single<GetStepByStepLessonQuestionsWithAnswersUseCase> {
        GetStepByStepLessonQuestionsWithAnswersUseCaseImpl(
            questionRepository = get()
        )
    }
    single<GetLessonQuestionsWithAnswersUseCase> {
        GetLessonQuestionsWithAnswersUseCaseImpl(
            getLessonQuestionsQueryOptionsUseCase = get(),
            getDefaultLessonQuestionsWithAnswersUseCase = get(),
            getStepByStepLessonQuestionsWithAnswersUseCase = get()
        )
    }

    viewModel {
        LessonQuestionsViewModel(
            getLessonQuestionsWithAnswersUseCase = get()
        )
    }

}