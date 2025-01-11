package cz.cvut.docta.course.data.local.source

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.core.data.local.dao.LocalUpdateTimeDao
import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise
import cz.cvut.docta.core.data.local.model.LocalUpdateTime
import cz.cvut.docta.core.data.model.TableName
import cz.cvut.docta.course.data.local.dao.CourseDao
import cz.cvut.docta.course.data.local.model.CourseEntity

class CourseLocalDataSourceImpl(
    private val courseDao: CourseDao,
    private val updateTimeDao: LocalUpdateTimeDao
) : CourseLocalDataSource {

    override suspend fun getUpdateTime(): Long? {
        return updateTimeDao.getUpdateTime(tableName = TableName.Course.name)
    }

    override suspend fun saveUpdateTime(timestamp: Long) {
        val updateTime = LocalUpdateTime(
            tableName = TableName.Course.name, courseCode = "", updateTime = timestamp
        )
        updateTimeDao.saveUpdateTime(updateTime = updateTime)
    }

    override suspend fun synchroniseCourses(
        coursesToSync: EntitiesToSynchronise<CourseEntity>,
        timestamp: Long
    ) {
        courseDao.deleteAndUpsertCourses(
            toDelete = coursesToSync.toDelete, toUpsert = coursesToSync.toUpsert
        )
        saveUpdateTime(timestamp = timestamp)
    }

    override suspend fun getAllCourses(): List<CourseEntity> {
        return courseDao.getAllCourses()
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        return courseDao.getCourse(courseCode = courseCode)
    }

}

fun courseLocalDataSourceFactory(appLocalDatabase: AppLocalDatabase): CourseLocalDataSource {
    return CourseLocalDataSourceImpl(
        courseDao = appLocalDatabase.courseDao(),
        updateTimeDao = appLocalDatabase.localUpdateTimeDao()
    )
}