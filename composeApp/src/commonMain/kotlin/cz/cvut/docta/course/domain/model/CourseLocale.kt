package cz.cvut.docta.course.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class CourseLocale(val langCode: String) {
    English(langCode = "en"),
    Czech(langCode = "cs");

    companion object {

        fun fromString(langCode: String): CourseLocale? = entries.find { it.langCode == langCode }

    }

}