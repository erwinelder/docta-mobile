package cz.cvut.docta.core.utils

import kotlin.enums.enumEntries


fun <T> T.asList(): List<T> {
    return listOf(this)
}


inline fun <reified T : Enum<T>> enumValueOrNull(name: String): T? {
    return enumEntries<T>().find { it.name == name }
}


fun <T, V> List<T>.excludeItems(items: List<T>, keySelector: (T) -> V): List<T> {
    return this.filter { item ->
        items.none { keySelector(item) == keySelector(it) }
    }
}
