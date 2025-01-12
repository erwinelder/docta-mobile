package cz.cvut.docta.lesson.data.remote.source

import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.lesson.data.remote.dao.LessonRemoteDao
import cz.cvut.docta.lesson.data.remote.mapper.toDefaultLessonDetailsList
import cz.cvut.docta.lesson.data.remote.mapper.toStepByStepLessonDetailsList
import cz.cvut.docta.lesson.data.remote.model.LessonRemoteDetails

class LessonRemoteDataSourceImpl(
    private val lessonDao: LessonRemoteDao,
    private val updateTimeDao: RemoteUpdateTimeDao
) : LessonRemoteDataSource {

    override suspend fun getUpdateTime(courseCode: String): Long? {
        return updateTimeDao.getUpdateTime(tableName = TableName.Lesson.name, courseCode = courseCode)
    }

    override suspend fun saveUpdateTime(courseCode: String, timestamp: Long) {
        updateTimeDao.saveUpdateTime(
            tableName = TableName.Lesson.name, updateTime = timestamp, courseCode = courseCode
        )
    }

    override suspend fun getLessonsAfterTimestamp(
        courseCode: String,
        timestamp: Long
    ): List<LessonRemoteDetails> {
        val defaultLessons = lessonDao
            .getDefaultLessonsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toDefaultLessonDetailsList()
        val stepByStepLessons = lessonDao
            .getStepByStepLessonsAfterTimestamp(courseCode = courseCode, timestamp = timestamp)
            .toStepByStepLessonDetailsList()

        return (defaultLessons + stepByStepLessons)
    }

}

fun lessonRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): LessonRemoteDataSource {
    return LessonRemoteDataSourceImpl(
        lessonDao = appRemoteDatabase.lessonRemoteDao(),
        updateTimeDao = appRemoteDatabase.remoteUpdateTimeDao()
    )
}