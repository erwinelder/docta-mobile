package cz.cvut.docta.di

import cz.cvut.docta.answer.data.local.source.AnswerLocalDataSource
import cz.cvut.docta.answer.data.local.source.answerLocalDataSourceFactory
import cz.cvut.docta.answer.data.repository.AnswerRepository
import cz.cvut.docta.answer.data.repository.AnswerRepositoryImpl
import cz.cvut.docta.answer.domain.usecase.GetAnswerOptionsQuestionWithAnswerUseCase
import cz.cvut.docta.answer.domain.usecase.GetAnswerOptionsQuestionWithAnswerUseCaseImpl
import cz.cvut.docta.answer.domain.usecase.GetFillInBlanksQuestionWithAnswersUseCase
import cz.cvut.docta.answer.domain.usecase.GetFillInBlanksQuestionWithAnswersUseCaseImpl
import cz.cvut.docta.answer.domain.usecase.GetOpenAnswerQuestionWithAnswersUseCase
import cz.cvut.docta.answer.domain.usecase.GetOpenAnswerQuestionWithAnswersUseCaseImpl
import cz.cvut.docta.answer.domain.usecase.GetQuestionAnswerPairsQuestionWithAnswersUseCase
import cz.cvut.docta.answer.domain.usecase.GetQuestionAnswerPairsQuestionWithAnswersUseCaseImpl
import org.koin.dsl.module

val answerModule = module {

    single<AnswerLocalDataSource> {
        answerLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<AnswerRepository> {
        AnswerRepositoryImpl(
            localDataSource = get()
        )
    }

    single<GetOpenAnswerQuestionWithAnswersUseCase> {
        GetOpenAnswerQuestionWithAnswersUseCaseImpl(
            answerRepository = get()
        )
    }
    single<GetFillInBlanksQuestionWithAnswersUseCase> {
        GetFillInBlanksQuestionWithAnswersUseCaseImpl(
            answerRepository = get()
        )
    }
    single<GetAnswerOptionsQuestionWithAnswerUseCase> {
        GetAnswerOptionsQuestionWithAnswerUseCaseImpl(
            answerRepository = get()
        )
    }
    single<GetQuestionAnswerPairsQuestionWithAnswersUseCase> {
        GetQuestionAnswerPairsQuestionWithAnswersUseCaseImpl(
            answerRepository = get()
        )
    }

}