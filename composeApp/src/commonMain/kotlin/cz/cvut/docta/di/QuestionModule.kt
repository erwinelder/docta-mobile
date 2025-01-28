package cz.cvut.docta.di

import cz.cvut.docta.lesson.domain.usecase.GetQuestionsQueryOptionsUseCase
import cz.cvut.docta.lesson.domain.usecase.GetQuestionsQueryOptionsUseCaseImpl
import cz.cvut.docta.question.data.local.source.QuestionLocalDataSource
import cz.cvut.docta.question.data.local.source.questionLocalDataSourceFactory
import cz.cvut.docta.question.data.remote.source.QuestionRemoteDataSource
import cz.cvut.docta.question.data.remote.source.questionRemoteDataSourceFactory
import cz.cvut.docta.question.data.repository.QuestionRepository
import cz.cvut.docta.question.data.repository.QuestionRepositoryImpl
import cz.cvut.docta.question.domain.usecase.GetDefaultLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetDefaultLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.question.domain.usecase.GetStepByStepLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.domain.usecase.GetStepByStepLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.question.presentation.viewmodel.AnswerOptionsQuestionViewModel
import cz.cvut.docta.question.presentation.viewmodel.FillInBlanksQuestionViewModel
import cz.cvut.docta.question.presentation.viewmodel.OpenAnswerQuestionViewModel
import cz.cvut.docta.question.presentation.viewmodel.QuestionAnswerPairsQuestionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val questionModule = module {

    /* ---------- Data Sources ---------- */

    single<QuestionLocalDataSource> {
        questionLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<QuestionRemoteDataSource> {
        questionRemoteDataSourceFactory(appRemoteDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<QuestionRepository> {
        QuestionRepositoryImpl(
            localSource = get(),
            remoteSource = get()
        )
    }

    /* ---------- Use Cases ---------- */

    single<GetQuestionsQueryOptionsUseCase> {
        GetQuestionsQueryOptionsUseCaseImpl(
            lessonRepository = get(),
            courseContext = get()
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
            getQuestionsQueryOptionsUseCase = get(),
            getDefaultLessonQuestionsWithAnswersUseCase = get(),
            getStepByStepLessonQuestionsWithAnswersUseCase = get()
        )
    }

    /* ---------- View Models ---------- */

    viewModel { parameters ->
        OpenAnswerQuestionViewModel(question = parameters.get())
    }

    viewModel { parameters ->
        FillInBlanksQuestionViewModel(question = parameters.get())
    }

    viewModel { parameters ->
        AnswerOptionsQuestionViewModel(question = parameters.get())
    }

    viewModel { parameters ->
        QuestionAnswerPairsQuestionViewModel(question = parameters.get())
    }

}