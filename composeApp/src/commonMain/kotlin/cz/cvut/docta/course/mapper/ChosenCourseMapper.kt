package cz.cvut.docta.course.mapper

import cz.cvut.docta.course.data.model.ChosenCourseEntity


fun List<ChosenCourseEntity>.toCodes(): List<String> {
    return map { it.code }
}


fun List<String>.toChosenCourseEntities(): List<ChosenCourseEntity> {
    return mapIndexed { index, code ->
        ChosenCourseEntity(
            code = code,
            orderNum = index
        )
    }
}