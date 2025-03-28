package cz.cvut.docta.di

import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepository
import cz.cvut.docta.lessonSession.data.repository.LessonSessionRepositoryImpl
import cz.cvut.docta.lessonSession.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.lessonSession.domain.usecase.GetLessonQuestionsWithAnswersUseCaseImpl
import cz.cvut.docta.lessonSession.presentation.viewmodel.AnswerOptionsQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.FillInBlanksQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.OpenAnswerQuestionViewModel
import cz.cvut.docta.lessonSession.presentation.viewmodel.QuestionAnswerPairsQuestionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lessonSessionModule = module {

    /* ---------- Repositories ---------- */

    single<LessonSessionRepository> {
        LessonSessionRepositoryImpl(userContext = get())
    }

    /* ---------- Use Cases ---------- */

    single<GetLessonQuestionsWithAnswersUseCase> {
        GetLessonQuestionsWithAnswersUseCaseImpl(
            courseContext = get(),
            lessonSessionRepository = get()
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