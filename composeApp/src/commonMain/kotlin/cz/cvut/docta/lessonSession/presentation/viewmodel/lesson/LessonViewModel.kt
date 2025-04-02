package cz.cvut.docta.lessonSession.presentation.viewmodel.lesson

import androidx.lifecycle.ViewModel
import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.domain.model.result.LessonSessionError
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.lessonSession.presentation.navigation.LessonSessionScreens
import cz.cvut.docta.lesson.presentation.utils.getLessonScreenToNavigateTo
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.lessonSession.mapper.toResultWithButtonState
import cz.cvut.docta.lessonSession.presentation.model.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.model.QuestionWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LessonViewModel(
    private val getLessonQuestionsWithAnswersUseCase: GetLessonQuestionsWithAnswersUseCase,
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


    private val incorrectAnsweredQuestions = mutableListOf<QuestionWrapper>()

    private fun addIncorrectAnsweredQuestion(question: QuestionWrapper) {
        incorrectAnsweredQuestions.add(question)
    }


    fun processQuestionCheckResult(questionCheckResult: QuestionCheckResult): Boolean {
        if (!questionCheckResult.isCorrect) {
            addIncorrectAnsweredQuestion(questionCheckResult.question)
        }
        return questionCheckResult.isCorrect
    }


    suspend fun fetchQuestions(sessionOptions: SessionOptions): Int {
        setRequestLoadingState()

        val questions = getLessonQuestionsWithAnswersUseCase
            .execute(sessionOptions = sessionOptions)
            .map { QuestionWrapper.Companion.fromQuestion(it) }

        if (questions.isNotEmpty()) {
            setQuestions(questions = questions)
        } else {
            _requestState.update {
                RequestState.Result(
                    resultState = LessonSessionError.LessonSessionIsEmpty.toResultWithButtonState()
                )
            }
        }

        return questions.size
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.generating_lesson_session)
        }
    }

    fun resetRequestState() {
        _requestState.update { null }
    }

}