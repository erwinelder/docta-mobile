package cz.cvut.docta.core.utils

import cz.cvut.docta.SharedRes
import dev.icerock.moko.resources.StringResource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun getCurrentLocalDateTime(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.UTC)
}
fun getCurrentLocalDate(): LocalDate {
    return getCurrentLocalDateTime().date
}


fun Int.getGreetingsWidgetTitleRes(): StringResource {
    return when (this) {
        in 6..11 -> SharedRes.strings.greetings_title_morning
        in 12..17 -> SharedRes.strings.greetings_title_afternoon
        in 18..22 -> SharedRes.strings.greetings_title_evening
        else -> SharedRes.strings.greetings_title_night
    }
}
