package cz.cvut.docta.lesson.data.local.source

import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics

interface LessonLocalDataSource {

    suspend fun getSectionLessons(sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(sectionId: Long): List<LessonDetailsWithStatistics>

}