package cz.cvut.docta.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.answer.data.local.dao.AnswerDao
import cz.cvut.docta.answer.data.local.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.local.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.local.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.local.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.local.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.local.model.QuestionAnswerPairTagEntity
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.LocalUpdateTime
import cz.cvut.docta.course.data.local.dao.CourseDao
import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course_draft.data.local.dao.CourseDraftDao
import cz.cvut.docta.course_draft.data.model.CourseDraftEntity
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.local.model.entity.DefaultLessonEntity
import cz.cvut.docta.lesson.data.local.model.entity.LessonEntity
import cz.cvut.docta.lesson.data.local.model.entity.StepByStepLessonEntity
import cz.cvut.docta.question.data.local.dao.QuestionDao
import cz.cvut.docta.question.data.local.model.entity.AnswerOptionsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.FillInBlanksQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.OpenAnswerQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionAnswerPairsQuestionEntity
import cz.cvut.docta.question.data.local.model.entity.QuestionEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.local.model.tag.QuestionTagEntity
import cz.cvut.docta.question.data.local.model.tag.QuestionTagQuestionAssociation
import cz.cvut.docta.question.data.local.model.entity.StepByStepLessonQuestionEntity
import cz.cvut.docta.section.data.local.dao.SectionDao
import cz.cvut.docta.section.data.local.model.SectionEntity
import cz.cvut.docta.section_draft.data.local.dao.SectionDraftDao
import cz.cvut.docta.section_draft.data.model.SectionDraftEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        LocalUpdateTime::class,

        CourseEntity::class,
        CourseDraftEntity::class,

        SectionEntity::class,
        SectionDraftEntity::class,

        LessonEntity::class,
        DefaultLessonEntity::class,
        StepByStepLessonEntity::class,

        QuestionEntity::class,
        OpenAnswerQuestionEntity::class,
        FillInBlanksQuestionEntity::class,
        AnswerOptionsQuestionEntity::class,
        QuestionAnswerPairsQuestionEntity::class,
        StepByStepLessonQuestionEntity::class,
        QuestionTagEntity::class,
        QuestionTagQuestionAssociation::class,
        QuestionTagDefaultLessonAssociation::class,

        CorrectOpenAnswerEntity::class,
        BlankAnswerEntity::class,
        AnswerOptionEntity::class,
        QuestionAnswerPairEntity::class,
        QuestionAnswerPairTagEntity::class,
        PairTagQuestionAssociation::class,
        PairTagPairAssociation::class,
    ],
    version = 1
)
@ConstructedBy(AppLocalDatabaseConstructor::class)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun localUpdateTimeDao(): LocalUpdateTimeDao
    abstract fun courseDao(): CourseDao
    abstract fun courseEditingDao(): CourseDraftDao
    abstract fun sectionDao(): SectionDao
    abstract fun sectionEditingDao(): SectionDraftDao
    abstract fun lessonDao(): LessonDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
}

fun getRoomLocalDatabase(builder: RoomDatabase.Builder<AppLocalDatabase>): AppLocalDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
