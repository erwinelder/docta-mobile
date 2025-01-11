package cz.cvut.docta.question.domain.usecase

import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairsQueryOptions
import cz.cvut.docta.answer.domain.usecase.GetAnswerOptionsQuestionWithAnswerUseCase
import cz.cvut.docta.answer.domain.usecase.GetFillInBlanksQuestionWithAnswersUseCase
import cz.cvut.docta.answer.domain.usecase.GetOpenAnswerQuestionWithAnswersUseCase
import cz.cvut.docta.answer.domain.usecase.GetQuestionAnswerPairsQuestionWithAnswersUseCase
import cz.cvut.docta.question.data.local.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.repository.QuestionRepository
import cz.cvut.docta.question.domain.model.QuestionWithAnswers

class GetDefaultLessonQuestionsWithAnswersUseCaseImpl(
    private val questionRepository: QuestionRepository,
    private val getOpenAnswerQuestionWithAnswersUseCase: GetOpenAnswerQuestionWithAnswersUseCase,
    private val getFillInBlanksQuestionWithAnswersUseCase: GetFillInBlanksQuestionWithAnswersUseCase,
    private val getAnswerOptionsQuestionWithAnswerUseCase: GetAnswerOptionsQuestionWithAnswerUseCase,
    private val getQuestionAnswerPairsQuestionWithAnswersUseCase: GetQuestionAnswerPairsQuestionWithAnswersUseCase
) : GetDefaultLessonQuestionsWithAnswersUseCase {
    override suspend fun execute(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionWithAnswers> {
        return questionRepository.getDefaultLessonQuestions(queryOptions = queryOptions)
            .mapNotNull { question ->
                when (question) {
                    is QuestionDetails.OpenAnswer ->
                        getOpenAnswerQuestionWithAnswersUseCase.execute(question = question)
                    is QuestionDetails.FillInBlanks ->
                        getFillInBlanksQuestionWithAnswersUseCase.execute(question = question)
                    is QuestionDetails.AnswerOptions ->
                        getAnswerOptionsQuestionWithAnswerUseCase.execute(question = question)
                    is QuestionDetails.QuestionAnswerPairs -> {
                        val pairsQueryOptions = QuestionAnswerPairsQueryOptions(
                            courseCode = queryOptions.courseCode,
                            difficulty = queryOptions.difficulty,
                            questionId = question.id
                        )
                        getQuestionAnswerPairsQuestionWithAnswersUseCase.execute(
                            question = question, queryOptions = pairsQueryOptions
                        )
                    }
                }
            }
    }
}