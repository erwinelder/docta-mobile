package cz.cvut.docta.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.cvut.docta.answer.data.model.AnswerEntity
import cz.cvut.docta.answer.data.model.AnswerPairEntity
import cz.cvut.docta.core.data.model.AnswerPairsQuestionToPairEntity
import cz.cvut.docta.core.data.model.LessonToQuestionEntity
import cz.cvut.docta.core.data.model.LocaleEntity
import cz.cvut.docta.core.data.model.QuestionToAnswerEntity
import cz.cvut.docta.core.data.model.UserEntity
import cz.cvut.docta.core.data.model.UserRoleEntity
import cz.cvut.docta.course.data.model.CourseEntity
import cz.cvut.docta.course.data.model.CourseSectionEntity
import cz.cvut.docta.course.data.model.LessonEntity
import cz.cvut.docta.question.data.model.AnswerPairsQuestionEntity
import cz.cvut.docta.question.data.model.MultipleChoiceQuestionEntity
import cz.cvut.docta.question.data.model.OpenAnswerQuestionEntity
import cz.cvut.docta.question.data.model.QuestionEntity
import cz.cvut.docta.question.data.model.SingleChoiceQuestionEntity

@Database(
    entities = [
        AnswerEntity::class,
        AnswerPairEntity::class,
        AnswerPairsQuestionToPairEntity::class,
        CourseSectionEntity::class,
        LessonToQuestionEntity::class,
        LocaleEntity::class,
        QuestionToAnswerEntity::class,
        UserEntity::class,
        UserRoleEntity::class,
        CourseEntity::class,
        CourseSectionEntity::class,
        LessonEntity::class,
        AnswerPairsQuestionEntity::class,
        MultipleChoiceQuestionEntity::class,
        OpenAnswerQuestionEntity::class,
        QuestionEntity::class,
        SingleChoiceQuestionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
}

