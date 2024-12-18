package cz.cvut.docta.question.domain.model

import cz.cvut.docta.answer.domain.model.AnswerPair

data class AnswerPairsQuestion(
    val questionId: Long,
    val answerPairs: List<AnswerPair>
)
