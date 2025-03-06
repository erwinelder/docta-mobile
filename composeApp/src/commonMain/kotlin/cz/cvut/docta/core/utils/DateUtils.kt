package cz.cvut.docta.core.utils

import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.greetings_title_afternoon
import docta.composeapp.generated.resources.greetings_title_evening
import docta.composeapp.generated.resources.greetings_title_morning
import docta.composeapp.generated.resources.greetings_title_night
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.StringResource


fun getCurrentLocalDateTime(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.UTC)
}
fun getCurrentLocalDate(): LocalDate {
    return getCurrentLocalDateTime().date
}


fun Int.getGreetingsWidgetTitleRes(): StringResource {
    return when (this) {
        in 6..11 -> Res.string.greetings_title_morning
        in 12..17 -> Res.string.greetings_title_afternoon
        in 18..22 -> Res.string.greetings_title_evening
        else -> Res.string.greetings_title_night
    }
}
