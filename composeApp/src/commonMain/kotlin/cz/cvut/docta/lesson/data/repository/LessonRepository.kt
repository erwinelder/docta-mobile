package cz.cvut.docta.lesson.data.repository

import cz.cvut.docta.lesson.data.model.LessonDetails
import cz.cvut.docta.lesson.data.model.LessonDetailsWithStatistics

interface LessonRepository {

    suspend fun getSectionLessons(sectionId: Long): List<LessonDetails>

    suspend fun getSectionLessonsWithStatistics(sectionId: Long): List<LessonDetailsWithStatistics>

}