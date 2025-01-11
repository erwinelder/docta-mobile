package cz.cvut.docta.answer.mapper

import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.domain.model.CorrectAnswer
import cz.cvut.docta.question.data.local.model.entity_with_details.QuestionDetails


fun List<CorrectOpenAnswerEntity>.toDomainOpenAnswers(questionId: Long): CorrectAnswer.OpenAnswers {
    return CorrectAnswer.OpenAnswers(
        questionId = questionId,
        answers = map { it.text }
    )
}

fun List<BlankAnswerEntity>.toDomainBlanks(questionId: Long): CorrectAnswer.Blanks {
    return CorrectAnswer.Blanks(
        questionId = questionId,
        blanksAnswers = this
            .groupBy { it.blankNum }
            .mapValues { (_, values) ->
                values.map { it.text }
            }
    )
}

fun QuestionDetails.AnswerOptions.toDomainOption(): CorrectAnswer.Option {
    return CorrectAnswer.Option(
        questionId = id,
        id = correctOptionId
    )
}
