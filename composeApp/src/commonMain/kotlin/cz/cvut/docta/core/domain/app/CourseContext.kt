package cz.cvut.docta.core.domain.app

import cz.cvut.docta.lesson.domain.model.Lesson

class CourseContext {

    private var courseCode: String = ""

    fun getCourseCode(): String {
        return courseCode
    }
    fun setCourseCode(courseCode: String) {
        this.courseCode = courseCode
    }
    fun resetCourseCode() {
        this.courseCode = ""
    }


    private var sectionId: Long = 0

    fun getSectionId(): Long {
        return sectionId
    }
    fun setSectionId(sectionId: Long) {
        this.sectionId = sectionId
    }
    fun resetSectionId() {
        this.sectionId = 0
    }


    private var lesson: Lesson? = null

    fun getLesson(): Lesson? {
        return lesson
    }
    fun setLesson(lesson: Lesson) {
        this.lesson = lesson
    }

}