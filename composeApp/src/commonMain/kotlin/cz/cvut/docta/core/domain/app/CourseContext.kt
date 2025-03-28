package cz.cvut.docta.core.domain.app

import cz.cvut.docta.lesson.domain.model.LessonWithProgress

class CourseContext {

    private var courseCode: String = ""

    fun getCourseCode(): String {
        return courseCode
    }
    fun setCourseCode(code: String) {
        this.courseCode = code
    }
    fun resetCourseCode() {
        this.courseCode = ""
    }


    private var sectionId: Int = 0

    fun getSectionId(): Int {
        return sectionId
    }
    fun setSectionId(sectionId: Int) {
        this.sectionId = sectionId
    }
    fun resetSectionId() {
        this.sectionId = 0
    }


    private var lesson: LessonWithProgress? = null

    fun getLesson(): LessonWithProgress? {
        return lesson
    }
    fun setLesson(lesson: LessonWithProgress) {
        this.lesson = lesson
    }

}