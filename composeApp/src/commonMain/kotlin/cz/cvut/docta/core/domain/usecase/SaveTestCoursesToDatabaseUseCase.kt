package cz.cvut.docta.core.domain.usecase

import cz.cvut.docta.answer.data.remote.dao.AnswerRemoteDao
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.course.data.remote.dao.CourseRemoteDao
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.lesson.data.local.model.DefaultLessonType
import cz.cvut.docta.lesson.data.local.model.LessonType
import cz.cvut.docta.lesson.data.remote.dao.LessonRemoteDao
import cz.cvut.docta.lesson.data.remote.model.entity.DefaultLessonRemoteEntity
import cz.cvut.docta.lesson.data.remote.model.entity.LessonRemoteEntity
import cz.cvut.docta.lesson.data.remote.model.entity.StepByStepLessonRemoteEntity
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.question.data.remote.dao.QuestionRemoteDao
import cz.cvut.docta.question.data.remote.model.entity.AnswerOptionsQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.FillInBlanksQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.OpenAnswerQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.QuestionAnswerPairsQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.QuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.entity.StepByStepLessonQuestionRemoteEntity
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagDefaultLessonRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagQuestionRemoteAssociation
import cz.cvut.docta.question.data.remote.model.tag.QuestionTagRemoteEntity
import cz.cvut.docta.question.domain.model.QuestionDifficulty
import cz.cvut.docta.section.data.remote.dao.SectionRemoteDao
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity

// Temporary use case for saving OMO course to database to test the main app functionalities
class SaveTestCoursesToDatabaseUseCase(
    private val updateTimeDao: RemoteUpdateTimeDao,
    private val courseDao: CourseRemoteDao,
    private val sectionDao: SectionRemoteDao,
    private val lessonDao: LessonRemoteDao,
    private val questionDao: QuestionRemoteDao,
    private val answerRemoteDao: AnswerRemoteDao
) {

    private val updateTime1 = 202501251355



    /* Course */

    private val courses = listOf(
        CourseRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            code = "nss",
            locale = "cs",
            name = "Objektový návrh a modelování",
        ),
    )



    /* Sections */

    private val sections = listOf(
        SectionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 1,
            orderNum = 1,
            name = "Vytváření objektů"
        ),
        SectionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 2,
            orderNum = 2,
            name = "Modelování chování aplikace"
        ),
    )



    /* Lessons */

    private val lessons = listOf(
        LessonRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            sectionId = 1,
            id = 1,
            type = LessonType.Default.name,
            orderNum = 1,
            name = "[name placeholder]",
            difficulty = LessonDifficulty.Easy.name
        ),
        LessonRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            sectionId = 1,
            id = 2,
            type = LessonType.Default.name,
            orderNum = 2,
            name = "[name placeholder]",
            difficulty = LessonDifficulty.Medium.name
        ),
        LessonRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            sectionId = 1,
            id = 3,
            type = LessonType.StepByStep.name,
            orderNum = 3,
            name = "[name placeholder]",
            difficulty = LessonDifficulty.Hard.name
        ),
    )



    /* Lessons - Step By Step */

    private val stepByStepLessons = listOf(
        StepByStepLessonRemoteEntity(
            lessonId = 3,
            description = "[description placeholder]",
        ),
    )

    /* Lessons - Step By Step - Questions */

    private val stepByStepLessonsQuestions = listOf(
        StepByStepLessonQuestionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            lessonId = 3,
            id = 1,
            orderNum = 1,
            questionText = "[question text placeholder]",
            correctAnswerText = "[answer text placeholder]",
        ),
    )



    /* Lessons - Default */

    private val defaultLessons = listOf(
        DefaultLessonRemoteEntity(
            lessonId = 1,
            defaultLessonType = DefaultLessonType.Default.name,
            matchAllTags = false
        ),
        DefaultLessonRemoteEntity(
            lessonId = 2,
            defaultLessonType = DefaultLessonType.Default.name,
            matchAllTags = false
        ),
    )



    /* Question Tags */

    private val questionTags = listOf(
        QuestionTagRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
        ),
    )

    /* Question Tag to Default Lesson Association */

    private val questionTagDefaultLessonAssociations = listOf(
        QuestionTagDefaultLessonRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
            lessonId = 1
        ),
    )

    /* Question Tag to Question Association */

    private val questionTagQuestionAssociations = listOf(
        QuestionTagQuestionRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
            questionId = 1
        ),
        QuestionTagQuestionRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
            questionId = 2
        ),
        QuestionTagQuestionRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
            questionId = 3
        ),
        QuestionTagQuestionRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-lesson1",
            questionId = 4
        ),
    )



    /* Questions */

    private val questions = listOf(
        QuestionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 1,
            difficulty = QuestionDifficulty.Easy.name,
        ),
        QuestionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 2,
            difficulty = QuestionDifficulty.Easy.name,
        ),
        QuestionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 3,
            difficulty = QuestionDifficulty.Easy.name,
        ),
        QuestionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 4,
            difficulty = QuestionDifficulty.Easy.name,
        ),
    )



    /* Questions - Open Answer */

    private val openAnswerQuestions = listOf(
        OpenAnswerQuestionRemoteEntity(
            questionId = 1,
            text = "[question text placeholder]",
        ),
    )

    /* Questions - Open Answer - Answers */

    private val openAnswerQuestionsAnswers = listOf(
        CorrectOpenAnswerRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 1,
            id = 1,
            text = "a",
        ),
    )



    /* Questions - Fill In Blanks */

    private val fillInBlanksQuestions = listOf(
        FillInBlanksQuestionRemoteEntity(
            questionId = 2,
            text = "question text ___ placeholder",
        ),
    )

    /* Questions - Fill In Blanks - Answers */

    private val fillInBlanksQuestionsAnswers = listOf(
        BlankAnswerRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 2,
            blankNum = 1,
            id = 1,
            text = "a",
        ),
    )



    /* Questions - Answer Options */

    private val answerOptionsQuestions = listOf(
        AnswerOptionsQuestionRemoteEntity(
            questionId = 3,
            text = "[question text placeholder]",
            correctOptionId = 1
        ),
    )

    /* Questions - Answer Options - Answers */

    private val answerOptionsQuestionsAnswers = listOf(
        AnswerOptionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 3,
            id = 1,
            text = "[1. answer text placeholder]",
        ),
        AnswerOptionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 3,
            id = 2,
            text = "[2. answer text placeholder]",
        ),
        AnswerOptionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 3,
            id = 3,
            text = "[3. answer text placeholder]",
        ),
        AnswerOptionRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            questionId = 3,
            id = 4,
            text = "[4. answer text placeholder]",
        ),
    )



    /* Questions - Question Answer Pair */

    private val questionAnswerPairsQuestions = listOf(
        QuestionAnswerPairsQuestionRemoteEntity(questionId = 4),
    )

    /* Questions - Question Answer Pair - Pairs */

    private val questionAnswerPairsQuestionsAnswers = listOf(
        QuestionAnswerPairRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            id = 1,
            difficulty = QuestionDifficulty.Easy.name,
            questionText = "[question text placeholder]",
            answerText = "[answer text placeholder]",
        ),
    )

    /* Question Answer Pair Tag */

    private val pairsTags = listOf(
        QuestionAnswerPairTagRemoteEntity(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-question1"
        )
    )

    /* Question Answer Pair Tag to Question Association */

    private val pairTagQuestionAssociations = listOf(
        PairTagQuestionRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-question1",
            questionId = 4
        )
    )

    /* Question Answer Pair Tag to Pair Association */

    private val pairTagPairAssociations = listOf(
        PairTagPairRemoteAssociation(
            updateTime = updateTime1,
            deleted = false,
            courseCode = "nss",
            tag = "nss-section5-question1",
            pairId = 1
        )
    )



    /* Saving course to database */

    suspend fun execute() {
//        if (courseDao.getCoursesAfterTimestamp(0).find { it.code == "nss" } != null) return


        updateTimeDao.saveUpdateTime(tableName = TableName.Course.name, updateTime = updateTime1)
        courseDao.upsertCourses(courses)

        updateTimeDao.saveUpdateTime(tableName = TableName.Section.name, updateTime = updateTime1, courseCode = "nss")
        sectionDao.upsertSections(sections)

        updateTimeDao.saveUpdateTime(tableName = TableName.Lesson.name, updateTime = updateTime1, courseCode = "nss")
        lessonDao.upsertLessons(lessons)
        lessonDao.upsertStepByStepLessons(stepByStepLessons)
        lessonDao.upsertDefaultLessons(defaultLessons)

        updateTimeDao.saveUpdateTime(tableName = TableName.Question.name, updateTime = updateTime1, courseCode = "nss")
        questionDao.upsertQuestions(questions)
        questionDao.upsertOpenAnswerQuestions(openAnswerQuestions)
        questionDao.upsertFillInBlanksQuestions(fillInBlanksQuestions)
        questionDao.upsertAnswerOptionsQuestions(answerOptionsQuestions)
        questionDao.upsertQuestionAnswerPairsQuestions(questionAnswerPairsQuestions)
        updateTimeDao.saveUpdateTime(tableName = TableName.StepByStepQuestion.name, updateTime = updateTime1, courseCode = "nss")
        questionDao.upsertStepByStepLessonQuestions(stepByStepLessonsQuestions)
        updateTimeDao.saveUpdateTime(tableName = TableName.QuestionTag.name, updateTime = updateTime1, courseCode = "nss")
        questionDao.upsertQuestionTags(questionTags)
        updateTimeDao.saveUpdateTime(tableName = TableName.QuestionTagDefaultLessonAssociation.name, updateTime = updateTime1, courseCode = "nss")
        questionDao.upsertQuestionTagDefaultLessonAssociations(questionTagDefaultLessonAssociations)
        updateTimeDao.saveUpdateTime(tableName = TableName.QuestionTagQuestionAssociation.name, updateTime = updateTime1, courseCode = "nss")
        questionDao.upsertQuestionTagQuestionAssociations(questionTagQuestionAssociations)

        updateTimeDao.saveUpdateTime(tableName = TableName.CorrectOpenAnswer.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertCorrectOpenAnswers(openAnswerQuestionsAnswers)
        updateTimeDao.saveUpdateTime(tableName = TableName.BlankAnswer.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertBlanksAnswers(fillInBlanksQuestionsAnswers)
        updateTimeDao.saveUpdateTime(tableName = TableName.AnswerOption.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertAnswerOptions(answerOptionsQuestionsAnswers)
        updateTimeDao.saveUpdateTime(tableName = TableName.QuestionAnswerPair.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertQuestionAnswerPairs(questionAnswerPairsQuestionsAnswers)
        updateTimeDao.saveUpdateTime(tableName = TableName.QuestionAnswerPairTag.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertPairTags(pairsTags)
        updateTimeDao.saveUpdateTime(tableName = TableName.PairTagQuestionAssociation.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertPairTagQuestionAssociations(pairTagQuestionAssociations)
        updateTimeDao.saveUpdateTime(tableName = TableName.PairTagPairAssociation.name, updateTime = updateTime1, courseCode = "nss")
        answerRemoteDao.upsertPairTagPairAssociations(pairTagPairAssociations)
    }

}