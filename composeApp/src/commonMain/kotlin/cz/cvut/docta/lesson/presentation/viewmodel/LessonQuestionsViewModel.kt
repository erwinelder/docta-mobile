package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.answer.domain.model.CorrectAnswer
import cz.cvut.docta.lesson.domain.model.LessonResults
import cz.cvut.docta.lesson.domain.model.LessonState
import cz.cvut.docta.question.domain.model.Question
import cz.cvut.docta.question.domain.model.QuestionCheckResult
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput
import cz.cvut.docta.question.domain.model.QuestionWithAnswers
import cz.cvut.docta.question.domain.usecase.GetLessonQuestionsWithAnswersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LessonQuestionsViewModel(
    private val getLessonQuestionsWithAnswersUseCase: GetLessonQuestionsWithAnswersUseCase
) : ViewModel() {

    private val _progression = MutableStateFlow(IntProgression.fromClosedRange(0, 1, 1))
    val progression = _progression.asStateFlow()

    private fun setProgression(rangeEnd: Int) {
        _progression.update {
            IntProgression.fromClosedRange(0, rangeEnd, 1)
        }
    }

    private fun incrementProgression() {
        _progression.update {
            it.step(it.step + 1)
        }
    }


    private val questionsWithInputs = mutableListOf<QuestionWithAnswerInput>()

    private fun setQuestions(questions: List<Question>) {
        questionsWithInputs.clear()
        val questionsWithAnswerInput = questions.map { QuestionWithAnswerInput.fromQuestion(it) }
        questionsWithInputs.addAll(questionsWithAnswerInput)
        applyNextQuestionOrShowResults()

        setProgression(questions.size)
    }


    private val questionsCorrectAnswers = mutableMapOf<Long, CorrectAnswer?>()

    private fun setCorrectAnswers(questionsWithAnswers: List<QuestionWithAnswers>) {
        questionsWithAnswers.forEach {
            questionsCorrectAnswers[it.getQuestion().id] = when (it) {
                is QuestionWithAnswers.OpenAnswer -> it.answers
                is QuestionWithAnswers.FillInBlanks -> it.blanksAnswers
                is QuestionWithAnswers.AnswerOptions -> it.answer
                is QuestionWithAnswers.QuestionAnswerPairs -> null
                is QuestionWithAnswers.StepByStep -> it.answer
            }
        }
    }

    private inline fun <reified T : CorrectAnswer> getCorrectAnswer(questionId: Long): T? {
        return questionsCorrectAnswers[questionId] as? T
    }


    private val wrongAnsweredQuestionsWithInputs = mutableListOf<QuestionWithAnswerInput>()

    private fun addWrongAnsweredQuestionWithInput(questionWithInput: QuestionWithAnswerInput) {
        wrongAnsweredQuestionsWithInputs.add(questionWithInput)
    }


    private val _lessonState = MutableStateFlow<LessonState?>(null)
    val lessonState = _lessonState.asStateFlow()

    private fun updateLessonQuestionState(questionWithInput: QuestionWithAnswerInput) {
        _lessonState.update {
            LessonState.LessonQuestion(question = questionWithInput)
        }
    }

    fun applyNextQuestionOrShowResults() {
        val nextQuestionWithInput = questionsWithInputs.removeFirstOrNull()

        if (nextQuestionWithInput != null) {
            _lessonState.update {
                LessonState.LessonQuestion(question = nextQuestionWithInput)
            }
        } else {
            val results = LessonResults(
                successRate = 0
            )
            _lessonState.update {
                LessonState.Results(lessonResults = results)
            }
        }

        incrementProgression()
    }

    private inline fun <reified T : QuestionWithAnswerInput> getQuestionWithAnswer(): T? {
        return (lessonState.value as? LessonState.LessonQuestion)?.question as? T
    }


    private val _questionCheckResult = MutableStateFlow<QuestionCheckResult?>(null)
    val questionCheckResult = _questionCheckResult.asStateFlow()

    private fun setAnswerCheckResult(
        questionWithInput: QuestionWithAnswerInput,
        isCorrect: Boolean
    ) {
        _questionCheckResult.update {
            it?.copy(
                isCorrect = isCorrect
            )
        }
    }

    fun resetAnswerCheckResult() {
        _questionCheckResult.update { null }
    }


    fun fetchQuestions(lessonId: Long) {
        viewModelScope.launch {
            val questionsWithAnswers = getLessonQuestionsWithAnswersUseCase.execute(
                lessonId = lessonId
            )
            setQuestions(questions = questionsWithAnswers.map { it.getQuestion() })
            setCorrectAnswers(questionsWithAnswers)
        }
    }


    /* ---------- OpenAnswerQuestion ---------- */

    fun changeOpenAnswerInput(value: String) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.OpenAnswer>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onInputChange(value)
            )
        )
    }

    fun checkOpenAnswer(): Boolean {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.OpenAnswer>()
            ?: return false

        val isCorrect = getCorrectAnswer<CorrectAnswer.OpenAnswers>(questionWithInput.question.id)
            ?.checkAnswer(questionWithInput.answerInput.answer)
            ?: return false

        setAnswerCheckResult(questionWithInput = questionWithInput, isCorrect = isCorrect)

        if (isCorrect) {
            // TODO-LESSON-STATISTICS
        } else {
            addWrongAnsweredQuestionWithInput(questionWithInput)
        }

        return isCorrect
    }

    /* ---------- FillInBlanksQuestion ---------- */

    fun changeBlankInput(blankNum: Int, value: String) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.FillInBlanks>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onBlankInputChange(
                    blankNum = blankNum, value = value
                )
            )
        )
    }

    fun checkBlanksAnswers(): Boolean {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.FillInBlanks>()
            ?: return false

        val isCorrect = getCorrectAnswer<CorrectAnswer.Blanks>(questionWithInput.question.id)
                ?.getWrongBlanksWithCorrectAnswer(questionWithInput.answerInput.answers)
                ?.isEmpty()
                ?: return false

        setAnswerCheckResult(questionWithInput = questionWithInput, isCorrect = isCorrect)

        if (isCorrect) {
            // TODO-QUESTION-RESULTS
            // TODO-LESSON-STATISTICS
        } else {
            addWrongAnsweredQuestionWithInput(questionWithInput)
        }

        return isCorrect
    }

    /* ---------- AnswerOptionsQuestionSection ---------- */

    fun selectOption(id: Long) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.AnswerOptions>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onOptionChoose(id)
            )
        )
    }

    fun checkOptionAnswer(): Boolean {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.AnswerOptions>()
            ?: return false

        val isCorrect = getCorrectAnswer<CorrectAnswer.Option>(questionWithInput.question.id)
            ?.checkOption(questionWithInput.answerInput.id)
            ?: return false

        setAnswerCheckResult(questionWithInput = questionWithInput, isCorrect = isCorrect)

        if (isCorrect) {
            // TODO-QUESTION-RESULTS
            // TODO-LESSON-STATISTICS
        } else {
            addWrongAnsweredQuestionWithInput(questionWithInput)
        }

        return isCorrect
    }

    /* ---------- QuestionAnswerQuestion ---------- */

    fun selectPairQuestion(id: Long) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.QuestionAnswerPairs>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onQuestionSelect(id)
            )
        )

        viewModelScope.launch {
            delay(300)
            tryStageQuestionAnswerPairInputChanges()
        }
    }
    fun selectPairAnswer(id: Long) {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.QuestionAnswerPairs>()
            ?: return

        updateLessonQuestionState(
            questionWithInput.copy(
                answerInput = questionWithInput.answerInput.onAnswerSelect(id)
            )
        )

        viewModelScope.launch {
            delay(300)
            tryStageQuestionAnswerPairInputChanges()
        }
    }
    private fun tryStageQuestionAnswerPairInputChanges() {
        val questionWithInput = getQuestionWithAnswer<QuestionWithAnswerInput.QuestionAnswerPairs>()
            ?: return

        val newQuestionWithInput = questionWithInput.answerInput.stageChanges()
            ?.let { questionWithInput.copy(answerInput = it) }
            ?: return

        updateLessonQuestionState(newQuestionWithInput)
        if (newQuestionWithInput.answerInput.allPairsAreMatched()) {
            showQuestionAnswerPairsQuestionResult()
        }
    }

    private fun showQuestionAnswerPairsQuestionResult() {
        // TODO-QUESTION-RESULTS
    }

    /* ---------- StepByStepQuestion ---------- */

    fun changeStepAnswerInput(value: String) {
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

        setAnswerCheckResult(questionWithInput = questionWithInput, isCorrect = isCorrect)

        if (isCorrect) {
            // TODO-QUESTION-RESULTS
            // TODO-LESSON-STATISTICS
        } else {
            addWrongAnsweredQuestionWithInput(questionWithInput)
        }

        return isCorrect
    }

}