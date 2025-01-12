package cz.cvut.docta.core.domain.course

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

}