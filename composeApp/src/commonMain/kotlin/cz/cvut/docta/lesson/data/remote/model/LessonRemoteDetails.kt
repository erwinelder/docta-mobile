package cz.cvut.docta.lesson.data.remote.model

import cz.cvut.docta.lesson.data.local.model.DefaultLessonType

sealed class LessonRemoteDetails(
    open val updateTime: Long,
    open val deleted: Boolean,
    open val courseCode: String,
    open val sectionId: Long,
    open val id: Long,
    open val orderNum: Int,
    open val name: String,
    open val difficulty: String
) {

    data class Default(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: String,
        val type: DefaultLessonType,
        val matchAllTags: Boolean
    ) : LessonRemoteDetails(updateTime, deleted, courseCode, sectionId, id, orderNum, name, difficulty)

    data class StepByStep(
        override val updateTime: Long,
        override val deleted: Boolean,
        override val courseCode: String,
        override val sectionId: Long,
        override val id: Long,
        override val orderNum: Int,
        override val name: String,
        override val difficulty: String,
        val description: String
    ) : LessonRemoteDetails(updateTime, deleted, courseCode, sectionId, id, orderNum, name, difficulty)

}