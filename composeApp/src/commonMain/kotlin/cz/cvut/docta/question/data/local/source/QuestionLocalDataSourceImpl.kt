package cz.cvut.docta.question.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.question.data.model.LessonQuestionsQueryOptions
import cz.cvut.docta.question.data.local.dao.QuestionDao
import cz.cvut.docta.question.data.mapper.toAnswerOptionsQuestionDetailsList
import cz.cvut.docta.question.data.mapper.toFillInBlanksQuestionDetailsList
import cz.cvut.docta.question.data.mapper.toOpenAnswerQuestionDetailsList
import cz.cvut.docta.question.data.mapper.toQuestionAnswerPairsQuestionDetailsList
import cz.cvut.docta.question.data.model.QuestionDetails
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity

class QuestionLocalDataSourceImpl(
    private val dao: QuestionDao
) : QuestionLocalDataSource {

    override suspend fun getDefaultLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.Default
    ): List<QuestionDetails> {
        val tags = dao.getTagsByLesson(lessonId = queryOptions.lessonId)
        val questionsIdsWithTags = dao.getQuestionsByCourseAndTags(
            courseCode = queryOptions.courseCode, tags = tags
        )

        val questionTags = questionsIdsWithTags
            .groupBy { it.questionId }
            .mapValues { it.value.map { questionIdWithTag -> questionIdWithTag.tag } }
            .let { questionIdToTagsMap ->
                if (queryOptions.matchAllTags) {
                    questionIdToTagsMap.filterValues { tags.containsAll(it) }
                } else {
                    questionIdToTagsMap
                }
            }
        val questionIds = questionTags.keys.toList()

        return getDefaultLessonQuestionsByIds(
            ids = questionIds, difficulty = queryOptions.difficulty
        )
    }

    private suspend fun getDefaultLessonQuestionsByIds(
        ids: List<Long>,
        difficulty: String
    ): List<QuestionDetails> {
        val openAnswerQuestions = dao
            .getOpenAnswerQuestions(ids = ids, difficulty = difficulty)
            .toOpenAnswerQuestionDetailsList()
        val fillInBlanksQuestions = dao
            .getFillInBlanksQuestions(ids = ids, difficulty = difficulty)
            .toFillInBlanksQuestionDetailsList()
        val answerOptionsQuestions = dao
            .getAnswerOptionsQuestions(ids = ids, difficulty = difficulty)
            .toAnswerOptionsQuestionDetailsList()
        val questionAnswerPairsQuestions = dao
            .getQuestionAnswerPairsQuestions(ids = ids, difficulty = difficulty)
            .toQuestionAnswerPairsQuestionDetailsList()

        return openAnswerQuestions + fillInBlanksQuestions + answerOptionsQuestions +
                questionAnswerPairsQuestions
    }


    override suspend fun getStepByStepLessonQuestions(
        queryOptions: LessonQuestionsQueryOptions.StepByStep
    ): List<StepByStepLessonQuestionEntity> {
        return dao.getStepByStepLessonQuestions(lessonId = queryOptions.lessonId)
    }

}

fun questionLocalDataSourceFactory(
    appLocalDatabase: AppLocalDatabase
): QuestionLocalDataSource {
    return QuestionLocalDataSourceImpl(dao = appLocalDatabase.questionDao())
}