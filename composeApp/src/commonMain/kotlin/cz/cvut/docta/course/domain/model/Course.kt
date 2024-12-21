package cz.cvut.docta.course.domain.model

import cz.cvut.docta.materials.domain.model.MaterialsGroup

data class Course(
    val code: String,
    val locale: String,
    val name: String,
    val materialsGroup: MaterialsGroup,
    val sections: List<CourseSectionLightweight>
)
