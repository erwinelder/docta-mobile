package cz.cvut.docta.question.mapper

import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.domain.model.AnswerText
import cz.cvut.docta.answer.domain.model.CorrectAnswer
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.question.domain.model.Question
import cz.cvut.docta.question.domain.model.QuestionDifficulty


fun QuestionDetails.OpenAnswer.toDomain(): Question.OpenAnswer? {
    val difficulty = QuestionDifficulty.entries.find { it.name == difficulty } ?: return null

    return Question.OpenAnswer(
        id = id,
        difficulty = difficulty,
        text = text
    )
}

fun QuestionDetails.FillInBlanks.toDomain(): Question.FillInBlanks? {
    val difficulty = QuestionDifficulty.entries.find { it.name == difficulty } ?: return null

    return Question.FillInBlanks(
        id = id,
        difficulty = difficulty,
        text = text
    )
}

fun QuestionDetails.AnswerOptions.toDomain(
    options: List<AnswerOptionEntity>
): Question.AnswerOptions? {
    val difficulty = QuestionDifficulty.entries.find { it.name == difficulty } ?: return null
    val optionsAsAnswersTexts = options.map { AnswerText(id = it.id, text = it.text) }

    return Question.AnswerOptions(
        id = id,
        difficulty = difficulty,
        text = text,
        options = optionsAsAnswersTexts
    )
}

fun QuestionDetails.QuestionAnswerPairs.toDomain(
    pairs: List<QuestionAnswerPairEntity>
): Question.QuestionAnswerPairs? {
    val difficulty = QuestionDifficulty.entries.find { it.name == difficulty } ?: return null
    val questions = pairs.map { AnswerText(id = it.id, text = it.questionText) }
    val answers = pairs.map { AnswerText(id = it.id, text = it.answerText) }

    return Question.QuestionAnswerPairs(
        id = id,
        difficulty = difficulty,
        questionPairs = questions,
        answerPairs = answers
    )
}

fun StepByStepLessonQuestionEntity.toDomainQuestion(): Question.StepByStep {
    return Question.StepByStep(
        id = id,
        text = questionText
    )
}

fun StepByStepLessonQuestionEntity.toDomainCorrectAnswer(): CorrectAnswer.StepAnswer {
    return CorrectAnswer.StepAnswer(
        questionId = id,
        answer = correctAnswerText
    )
}