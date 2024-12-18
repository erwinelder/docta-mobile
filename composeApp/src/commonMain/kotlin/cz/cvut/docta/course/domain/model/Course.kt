package cz.cvut.docta.course.domain.model

data class Course(
    val code: String,
    val locale: String,
    val name: String,
//    val materialsGroup: MaterialsGroup,
    val sections: List<CourseSection>
)
