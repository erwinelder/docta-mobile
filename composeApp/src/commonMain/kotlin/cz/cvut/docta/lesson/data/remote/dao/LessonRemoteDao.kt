package cz.cvut.docta.lesson.data.remote.dao

import androidx.room.Dao
import androidx.room.Query
import cz.cvut.docta.lesson.data.remote.model.entity_with_details.DefaultLessonRemoteWithDetails
import cz.cvut.docta.lesson.data.remote.model.entity_with_details.StepByStepLessonRemoteWithDetails

@Dao
interface LessonRemoteDao {

    @Query("""
        SELECT * FROM lesson_remote
        INNER JOIN default_lesson_remote ON lesson_remote.id = default_lesson_remote.lessonId
        WHERE lesson_remote.courseCode = :courseCode AND lesson_remote.updateTime > :timestamp
    """)
    suspend fun getDefaultLessonsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<DefaultLessonRemoteWithDetails>

    @Query("""
        SELECT * FROM lesson_remote
        INNER JOIN step_by_step_lesson_remote ON lesson_remote.id = step_by_step_lesson_remote.lessonId
        WHERE lesson_remote.courseCode = :courseCode AND lesson_remote.updateTime > :timestamp
    """)
    suspend fun getStepByStepLessonsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<StepByStepLessonRemoteWithDetails>

}