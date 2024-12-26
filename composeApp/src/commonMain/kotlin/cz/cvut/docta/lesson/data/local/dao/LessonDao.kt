package cz.cvut.docta.lesson.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.lesson.data.model.DefaultLessonWithDetails
import cz.cvut.docta.lesson.data.model.StepByStepLessonWithDetails

@Dao
interface LessonDao {

    @Query("""
        SELECT lesson.sectionId, lesson.id AS id, lesson.orderNum, lesson.name,
               lesson.difficulty, default_lesson.type, default_lesson.matchAllTags
        FROM lesson
        INNER JOIN default_lesson ON lesson.id = default_lesson.lessonId
        WHERE lesson.sectionId = :sectionId
    """)
    suspend fun getDefaultLessons(sectionId: Long): List<DefaultLessonWithDetails>

    @Query("""
        SELECT lesson.sectionId, lesson.id AS id, lesson.orderNum, lesson.name,
               lesson.difficulty, step_by_step_lesson.description
        FROM lesson
        INNER JOIN step_by_step_lesson ON lesson.id = step_by_step_lesson.lessonId
        WHERE lesson.sectionId = :sectionId
    """)
    suspend fun getStepByStepLessons(sectionId: Long): List<StepByStepLessonWithDetails>

}