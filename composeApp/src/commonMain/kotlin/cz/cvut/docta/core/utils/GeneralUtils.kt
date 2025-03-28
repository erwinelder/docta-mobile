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


fun takeActionIf(condition: Boolean, action: () -> Unit): (() -> Unit)? {
    return if (condition) {
        action
    } else {
        null
    }
}

fun <T, R> takeActionIf(condition: Boolean, action: (T) -> R): ((T) -> R)? {
    return if (condition) {
        action
    } else {
        null
    }
}