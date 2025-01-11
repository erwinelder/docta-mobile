package cz.cvut.docta.lesson.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import cz.cvut.docta.lesson.data.local.model.entity.DefaultLessonEntity
import cz.cvut.docta.lesson.data.local.model.entity_with_details.DefaultLessonWithDetails
import cz.cvut.docta.lesson.data.local.model.entity.LessonEntity
import cz.cvut.docta.lesson.data.local.model.entity.StepByStepLessonEntity
import cz.cvut.docta.lesson.data.local.model.entity_with_details.StepByStepLessonWithDetails

@Dao
interface LessonDao {

    @Query("SELECT lesson.type FROM lesson WHERE lesson.id = :lessonId")
    suspend fun getLessonType(lessonId: Long): String


    @Upsert
    suspend fun upsertLessons(lessons: List<LessonEntity>)

    @Delete
    suspend fun deleteLessons(lessons: List<LessonEntity>)

    @Transaction
    suspend fun deleteAndUpsertLessonsAndInheritedLessons(
        lessonsToDelete: List<LessonEntity>,
        lessonsToUpsert: List<LessonEntity>,
        defaultLessonsToUpsert: List<DefaultLessonEntity>,
        stepByStepLessonsToUpsert: List<StepByStepLessonEntity>
    ) {
        deleteLessons(lessonsToDelete)
        upsertLessons(lessonsToUpsert)

        upsertDefaultLessons(defaultLessonsToUpsert)
        upsertStepByStepLessons(stepByStepLessonsToUpsert)
    }


    @Upsert
    suspend fun upsertDefaultLessons(defaultLessons: List<DefaultLessonEntity>)

    @Query("""
        SELECT * FROM lesson
        INNER JOIN default_lesson ON lesson.id = default_lesson.lessonId
        WHERE lesson.id = :lessonId
    """)
    suspend fun getDefaultLesson(lessonId: Long): DefaultLessonWithDetails?

    @Query("""
        SELECT * FROM lesson
        INNER JOIN default_lesson ON lesson.id = default_lesson.lessonId
        WHERE lesson.sectionId = :sectionId
    """)
    suspend fun getSectionDefaultLessons(sectionId: Long): List<DefaultLessonWithDetails>


    @Upsert
    suspend fun upsertStepByStepLessons(stepByStepLessons: List<StepByStepLessonEntity>)

    @Query("""
        SELECT * FROM lesson
        INNER JOIN step_by_step_lesson ON lesson.id = step_by_step_lesson.lessonId
        WHERE lesson.sectionId = :sectionId
    """)
    suspend fun getSectionStepByStepLessons(sectionId: Long): List<StepByStepLessonWithDetails>

}