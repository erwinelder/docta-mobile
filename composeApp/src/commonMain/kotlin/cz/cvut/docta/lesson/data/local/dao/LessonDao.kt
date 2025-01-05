package cz.cvut.docta.lesson.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.lesson.data.model.DefaultLessonWithDetails
import cz.cvut.docta.lesson.data.model.StepByStepLessonWithDetails

@Dao
interface LessonDao {

    @Query("SELECT lesson.type FROM lesson WHERE lesson.id = :lessonId")
    suspend fun getLessonType(lessonId: Long): String

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

    @Query("""
        SELECT * FROM lesson
        INNER JOIN step_by_step_lesson ON lesson.id = step_by_step_lesson.lessonId
        WHERE lesson.sectionId = :sectionId
    """)
    suspend fun getSectionStepByStepLessons(sectionId: Long): List<StepByStepLessonWithDetails>

}