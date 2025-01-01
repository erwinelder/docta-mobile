package cz.cvut.docta.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.answer.data.local.dao.AnswerDao
import cz.cvut.docta.answer.data.model.AnswerOptionEntity
import cz.cvut.docta.answer.data.model.BlankAnswerEntity
import cz.cvut.docta.answer.data.model.CorrectOpenAnswerEntity
import cz.cvut.docta.answer.data.model.PairTagPairAssociation
import cz.cvut.docta.answer.data.model.PairTagQuestionAssociation
import cz.cvut.docta.answer.data.model.QuestionAnswerPairEntity
import cz.cvut.docta.answer.data.model.QuestionAnswerPairTagEntity
import cz.cvut.docta.course.data.local.dao.CourseDao
import cz.cvut.docta.section.data.local.dao.SectionDao
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.lesson.data.local.dao.LessonDao
import cz.cvut.docta.lesson.data.model.DefaultLessonEntity
import cz.cvut.docta.lesson.data.model.LessonEntity
import cz.cvut.docta.lesson.data.model.StepByStepLessonEntity
import cz.cvut.docta.question.data.local.dao.QuestionDao
import cz.cvut.docta.question.data.model.AnswerOptionsQuestionEntity
import cz.cvut.docta.question.data.model.FillInBlanksQuestionEntity
import cz.cvut.docta.question.data.model.OpenAnswerQuestionEntity
import cz.cvut.docta.question.data.model.QuestionAnswerPairsQuestionEntity
import cz.cvut.docta.question.data.model.QuestionEntity
import cz.cvut.docta.question.data.model.QuestionTagDefaultLessonAssociation
import cz.cvut.docta.question.data.model.QuestionTagEntity
import cz.cvut.docta.question.data.model.QuestionTagQuestionAssociation
import cz.cvut.docta.question.data.model.StepByStepLessonQuestionEntity
import cz.cvut.docta.section.data.model.SectionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        CourseEntity::class,

        SectionEntity::class,

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
    version = 3
)
@ConstructedBy(AppLocalDatabaseConstructor::class)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun sectionDao(): SectionDao
    abstract fun lessonDao(): LessonDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
}

fun getRoomDatabase(builder: RoomDatabase.Builder<AppLocalDatabase>): AppLocalDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
