package cz.cvut.docta.course.data.repository

import cz.cvut.docta.core.data.utils.synchroniseData
import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.mapper.toCourseEntitiesToSync
import cz.cvut.docta.course.data.local.model.CourseEntity
import cz.cvut.docta.course.data.remote.source.CourseRemoteDataSource
import cz.cvut.docta.course.data.remote.model.CourseRemoteEntity

class CourseRepositoryImpl(
    private val localSource: CourseLocalDataSource,
    private val remoteSource: CourseRemoteDataSource
) : CourseRepository {

    private suspend fun synchroniseCourses() {
        synchroniseData(
            localUpdateTimeGetter = { localSource.getUpdateTime() },
            remoteUpdateTimeGetter = { remoteSource.getUpdateTime() },
            remoteDataGetter = { _, timestamp ->
                remoteSource.getCoursesAfterTimestamp(timestamp = timestamp)
            },
            remoteDataToDataToSyncMapper = List<CourseRemoteEntity>::toCourseEntitiesToSync,
            localSynchroniser = { coursesToSync, _, timestamp ->
                localSource.synchroniseCourses(coursesToSync = coursesToSync, timestamp = timestamp)
            }
        )
    }

    override suspend fun getAllCourses(): List<CourseEntity> {
        synchroniseCourses()
        return localSource.getAllCourses()
    }

    override suspend fun getCourse(courseCode: String): CourseEntity? {
        synchroniseCourses()
        return localSource.getCourse(courseCode = courseCode)
    }

}
