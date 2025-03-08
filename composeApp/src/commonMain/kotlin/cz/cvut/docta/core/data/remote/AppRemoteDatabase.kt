package cz.cvut.docta.core.data.remote

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cz.cvut.docta.answer.data.remote.dao.AnswerRemoteDao
import cz.cvut.docta.answer.data.remote.model.AnswerOptionRemoteEntity
import cz.cvut.docta.answer.data.remote.model.BlankAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.CorrectOpenAnswerRemoteEntity
import cz.cvut.docta.answer.data.remote.model.PairTagPairRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.PairTagQuestionRemoteAssociation
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairRemoteEntity
import cz.cvut.docta.answer.data.remote.model.QuestionAnswerPairTagRemoteEntity
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.core.data.remote.model.RemoteUpdateTime
import cz.cvut.docta.course.data.remote.dao.CourseRemoteDao
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity
import cz.cvut.docta.lesson.data.remote.dao.LessonRemoteDao
import cz.cvut.docta.lesson.data.remote.model.entity.DefaultLessonRemoteEntity
import cz.cvut.docta.lesson.data.remote.model.entity.LessonRemoteEntity
import cz.cvut.docta.lesson.data.remote.model.entity.StepByStepLessonRemoteEntity
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
import cz.cvut.docta.section.data.remote.dao.SectionRemoteDao
import cz.cvut.docta.section.data.remote.model.SectionRemoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        RemoteUpdateTime::class,

        CourseRemoteEntity::class,

        SectionRemoteEntity::class,

        LessonRemoteEntity::class,
        DefaultLessonRemoteEntity::class,
        StepByStepLessonRemoteEntity::class,

        QuestionRemoteEntity::class,
        OpenAnswerQuestionRemoteEntity::class,
        FillInBlanksQuestionRemoteEntity::class,
        AnswerOptionsQuestionRemoteEntity::class,
        QuestionAnswerPairsQuestionRemoteEntity::class,
        StepByStepLessonQuestionRemoteEntity::class,
        QuestionTagRemoteEntity::class,
        QuestionTagQuestionRemoteAssociation::class,
        QuestionTagDefaultLessonRemoteAssociation::class,

        CorrectOpenAnswerRemoteEntity::class,
        BlankAnswerRemoteEntity::class,
        AnswerOptionRemoteEntity::class,
        QuestionAnswerPairRemoteEntity::class,
        QuestionAnswerPairTagRemoteEntity::class,
        PairTagQuestionRemoteAssociation::class,
        PairTagPairRemoteAssociation::class,
    ],
    version = 2
)
@ConstructedBy(AppRemoteDatabaseConstructor::class)
abstract class AppRemoteDatabase : RoomDatabase() {
    abstract fun remoteUpdateTimeDao(): RemoteUpdateTimeDao
    abstract fun courseRemoteDao(): CourseRemoteDao
    abstract fun sectionRemoteDao(): SectionRemoteDao
    abstract fun lessonRemoteDao(): LessonRemoteDao
    abstract fun questionRemoteDao(): QuestionRemoteDao
    abstract fun answerRemoteDao(): AnswerRemoteDao
}

fun getRoomRemoteDatabase(builder: RoomDatabase.Builder<AppRemoteDatabase>): AppRemoteDatabase {
    return builder
//        .addMigrations()
        .fallbackToDestructiveMigration(dropAllTables = true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
