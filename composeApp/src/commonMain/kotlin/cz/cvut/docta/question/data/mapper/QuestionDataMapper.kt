package cz.cvut.docta.question.data.mapper

import cz.cvut.docta.question.data.model.AnswerOptionsQuestionWithDetails
import cz.cvut.docta.question.data.model.FillInBlanksQuestionWithDetails
import cz.cvut.docta.question.data.model.OpenAnswerQuestionWithDetails
import cz.cvut.docta.question.data.model.QuestionAnswerPairsQuestionWithDetails
import cz.cvut.docta.question.data.model.QuestionDetails


fun List<OpenAnswerQuestionWithDetails>.toOpenAnswerQuestionDetailsList() =
    map { it.toQuestionDetails() }


fun OpenAnswerQuestionWithDetails.toQuestionDetails(): QuestionDetails.OpenAnswer {
    return QuestionDetails.OpenAnswer(
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<FillInBlanksQuestionWithDetails>.toFillInBlanksQuestionDetailsList() =
    map { it.toQuestionDetails() }

fun FillInBlanksQuestionWithDetails.toQuestionDetails(): QuestionDetails.FillInBlanks {
    return QuestionDetails.FillInBlanks(
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<AnswerOptionsQuestionWithDetails>.toAnswerOptionsQuestionDetailsList() =
    map { it.toQuestionDetails() }

fun AnswerOptionsQuestionWithDetails.toQuestionDetails(): QuestionDetails.AnswerOptions {
    return QuestionDetails.AnswerOptions(
        id = id,
        difficulty = difficulty,
        text = text,
        correctOptionId = correctOptionId
    )
}


fun List<QuestionAnswerPairsQuestionWithDetails>.toQuestionAnswerPairsQuestionDetailsList() =
    map { it.toQuestionDetails() }

fun QuestionAnswerPairsQuestionWithDetails.toQuestionDetails(): QuestionDetails.QuestionAnswerPairs {
    return QuestionDetails.QuestionAnswerPairs(
        id = id,
        difficulty = difficulty
    )
}