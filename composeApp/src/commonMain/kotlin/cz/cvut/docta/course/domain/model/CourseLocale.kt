package cz.cvut.docta.course.domain.model

sealed class CourseLocale(val langCode: String) {

    data object English : CourseLocale(langCode = "en")

    data object Czech : CourseLocale(langCode = "cs")


    companion object {

        fun fromString(locale: String): CourseLocale? {
            return when (locale) {
                "en" -> English
                "cs" -> Czech
                else -> null
            }
        }

    }

}