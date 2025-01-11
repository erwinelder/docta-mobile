package cz.cvut.docta.core.data.utils

import cz.cvut.docta.core.data.local.model.EntitiesToSynchronise


fun needToSynchroniseData(localTimestamp: Long?, remoteTimestamp: Long?): Pair<Long?, Long>? {
    if (remoteTimestamp == null) return null
    if (localTimestamp != null && localTimestamp >= remoteTimestamp) return null

    return Pair(localTimestamp, remoteTimestamp)
}

suspend fun <LE, RE> synchroniseData(
    courseCode: String = "",
    localUpdateTimeGetter: suspend (courseCode: String) -> Long?,
    remoteUpdateTimeGetter: suspend (courseCode: String) -> Long?,
    remoteDataGetter: suspend (courseCode: String, timestamp: Long) -> List<RE>,
    remoteDataToDataToSyncMapper: (List<RE>) -> EntitiesToSynchronise<LE>,
    localSynchroniser: suspend (EntitiesToSynchronise<LE>, courseCode: String, timestamp: Long) -> Unit
) {
    val (localTimestamp, remoteTimestamp) = needToSynchroniseData(
        localTimestamp = localUpdateTimeGetter(courseCode),
        remoteTimestamp = remoteUpdateTimeGetter(courseCode)
    ) ?: return

    val dataToSync = remoteDataGetter(courseCode, localTimestamp ?: 0)
        .let(remoteDataToDataToSyncMapper)

    localSynchroniser(dataToSync, courseCode, remoteTimestamp)
}