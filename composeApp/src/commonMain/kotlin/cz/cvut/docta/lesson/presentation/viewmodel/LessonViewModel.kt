package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import cz.cvut.docta.lesson.presentation.navigation.LessonScreens
import cz.cvut.docta.lesson.presentation.utils.getLessonScreenToNavigateTo
import cz.cvut.docta.question.domain.model.QuestionWithCheckResult
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import cz.cvut.docta.question.presentation.model.QuestionAndAnswersWrapper

class LessonViewModel(
    private val getLessonQuestionsWithAnswersUseCase: GetLessonQuestionsWithAnswersUseCase,
    // TODO-STATISTICS: add statistics use case
) : ViewModel() {

    /*
    TODO-STATISTICS: create private statistics state, then save this to data layer when lesson
     is finished
    */


    private val questions = mutableListOf<QuestionAndAnswersWrapper>()

    private fun setQuestions(questions: List<QuestionAndAnswersWrapper>) {
        this.questions.clear()
        this.questions.addAll(questions)
    }

    fun getNextQuestionOrNull(): QuestionAndAnswersWrapper? {
        return questions.getOrNull(0)
    }

    fun getNextQuestion(): QuestionAndAnswersWrapper {
        return questions[0]
    }

    inline fun <reified T : QuestionAndAnswersWrapper> getNextQuestionAs(): T {
        return getNextQuestion() as T
    }

    fun processToNextQuestion(): LessonScreens? {
        questions.removeAt(0)
        return getNextQuestionOrNull()?.getLessonScreenToNavigateTo()
    }


    private val wrongAnsweredQuestions = mutableListOf<QuestionAndAnswersWrapper>()

    private fun addWrongAnsweredQuestion(question: QuestionAndAnswersWrapper) {
        wrongAnsweredQuestions.add(question)
    }


    // TODO-STATISTICS: update statistics state here in view model
    fun processQuestionCheckResult(questionWithCheckResult: QuestionWithCheckResult) {
        if (!questionWithCheckResult.result.isCorrect) {
            addWrongAnsweredQuestion(questionWithCheckResult.question)
        }
    }


    suspend fun fetchQuestions(lessonId: Long): Int {
        val questions = getLessonQuestionsWithAnswersUseCase.execute(lessonId = lessonId)
            .map { QuestionAndAnswersWrapper.fromQuestion(it) }

        setQuestions(questions = questions)
        return questions.size
    }

    /* ---------- StepByStepQuestion ---------- */

    /*fun changeStepAnswerInput(value: String) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.Step>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onInputChange(value)
            )
        )
    }

    fun checkStepAnswer(): Boolean {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.Step>()
            ?: return false

        val isCorrect = getCorrectAnswer<CorrectAnswer.StepAnswer>(questionWithInput.question.id)
            ?.checkAnswer(questionWithInput.answerInput.answer)
            ?: return false

//        setAnswerCheckResult(questionWithInput = questionWithInput, isCorrect = isCorrect)

        if (isCorrect) {
            // TODO-QUESTION-RESULTS
            // TODO-LESSON-STATISTICS
        } else {
            addWrongAnsweredQuestionWithInput(questionWithInput)
        }

        return isCorrect
    }*/

}