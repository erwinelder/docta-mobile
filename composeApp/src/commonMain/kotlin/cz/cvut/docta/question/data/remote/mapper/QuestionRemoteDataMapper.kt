package cz.cvut.docta.question.data.remote.mapper

import cz.cvut.docta.question.data.remote.model.QuestionRemoteDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.AnswerOptionsQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.FillInBlanksQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.OpenAnswerQuestionRemoteWithDetails
import cz.cvut.docta.question.data.remote.model.entity_with_details.QuestionAnswerPairsQuestionRemoteWithDetails


fun List<OpenAnswerQuestionRemoteWithDetails>.toSealedOpenAnswerQuestionDetails() =
    map { it.wrapInSealedClass() }

fun OpenAnswerQuestionRemoteWithDetails.wrapInSealedClass(): QuestionRemoteDetails.OpenAnswer {
    return QuestionRemoteDetails.OpenAnswer(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<FillInBlanksQuestionRemoteWithDetails>.toSealedFillInBlanksQuestionDetails() =
    map { it.wrapInSealedClass() }

fun FillInBlanksQuestionRemoteWithDetails.wrapInSealedClass(): QuestionRemoteDetails.FillInBlanks {
    return QuestionRemoteDetails.FillInBlanks(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        id = id,
        difficulty = difficulty,
        text = text
    )
}


fun List<AnswerOptionsQuestionRemoteWithDetails>.toSealedAnswerOptionsQuestionDetails() =
    map { it.wrapInSealedClass() }

fun AnswerOptionsQuestionRemoteWithDetails.wrapInSealedClass(): QuestionRemoteDetails.AnswerOptions {
    return QuestionRemoteDetails.AnswerOptions(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        id = id,
        difficulty = difficulty,
        text = text,
        correctOptionId = correctOptionId
    )
}


fun List<QuestionAnswerPairsQuestionRemoteWithDetails>.toSealedQuestionAnswerPairsQuestionDetails() =
    map { it.wrapInSealedClass() }

fun QuestionAnswerPairsQuestionRemoteWithDetails.wrapInSealedClass(): QuestionRemoteDetails.QuestionAnswerPairs {
    return QuestionRemoteDetails.QuestionAnswerPairs(
        updateTime = updateTime,
        deleted = deleted,
        courseCode = courseCode,
        id = id,
        difficulty = difficulty
    )
}