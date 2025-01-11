package cz.cvut.docta.course.data.remote.source

import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.dao.RemoteUpdateTimeDao
import cz.cvut.docta.core.data.remote.model.RemoteUpdateTime
import cz.cvut.docta.course.data.remote.dao.CourseRemoteDao
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

class CourseRemoteDataSourceImpl(
    private val courseDao: CourseRemoteDao,
    private val updateTimeDao: RemoteUpdateTimeDao
) : CourseRemoteDataSource {

    override suspend fun getUpdateTime(): Long? {
        return updateTimeDao.getUpdateTime(tableName = TableName.Course.name)
    }

    override suspend fun saveUpdateTime(timestamp: Long) {
        val updateTime = RemoteUpdateTime(
            tableName = TableName.Course.name, courseCode = "", updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun getCoursesAfterTimestamp(
        timestamp: Long
    ): List<CourseRemoteEntity> {
        return courseDao.getCoursesAfterTimestamp(timestamp = timestamp)
    }

}

fun courseRemoteDataSourceFactory(appRemoteDatabase: AppRemoteDatabase): CourseRemoteDataSource {
    return CourseRemoteDataSourceImpl(
        courseDao = appRemoteDatabase.courseRemoteDao(),
        updateTimeDao = appRemoteDatabase.remoteUpdateTimeDao()
    )
}