package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.docta.lesson.presentation.navigation.LessonSessionScreens
import cz.cvut.docta.lesson.presentation.utils.getLessonScreenToNavigateTo
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCheckResult
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper

class LessonViewModel(
    private val getLessonQuestionsWithAnswersUseCase: GetLessonQuestionsWithAnswersUseCase
) : ViewModel() {

    private val questions = mutableListOf<QuestionWrapper>()

    private fun setQuestions(questions: List<QuestionWrapper>) {
        this.questions.clear()
        this.questions.addAll(questions)
    }

    fun getNextQuestionOrNull(): QuestionWrapper? {
        return questions.getOrNull(0)
    }

    fun getNextQuestion(): QuestionWrapper {
        return questions[0]
    }

    inline fun <reified T : QuestionWrapper> getNextQuestionAs(): T {
        return getNextQuestion() as T
    }

    fun processToNextQuestion(): LessonSessionScreens? {
        questions.removeAt(0)
        return getNextQuestionOrNull()?.getLessonScreenToNavigateTo()
    }


    private val wrongAnsweredQuestions = mutableListOf<QuestionWrapper>()

    private fun addWrongAnsweredQuestion(question: QuestionWrapper) {
        wrongAnsweredQuestions.add(question)
    }


    fun processQuestionCheckResult(questionWithCheckResult: QuestionWithCheckResult) {
        if (!questionWithCheckResult.isCorrect) {
            addWrongAnsweredQuestion(questionWithCheckResult.question)
        }
    }


    suspend fun fetchQuestions(sessionOptions: SessionOptions): Int {
        val questions = getLessonQuestionsWithAnswersUseCase
            .execute(sessionOptions = sessionOptions)
            .map { QuestionWrapper.fromQuestion(it) }

        setQuestions(questions = questions)
        return questions.size
    }

}