package cz.cvut.docta.core.utils

import kotlin.enums.enumEntries


inline fun <reified T : Enum<T>> enumValueOrNull(name: String): T? {
    return enumEntries<T>().find { it.name == name }
}