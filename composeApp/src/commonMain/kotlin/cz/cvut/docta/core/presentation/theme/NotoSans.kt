package cz.cvut.docta.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.notosans_black
import docta.composeapp.generated.resources.notosans_bold
import docta.composeapp.generated.resources.notosans_extrabold
import docta.composeapp.generated.resources.notosans_extralight
import docta.composeapp.generated.resources.notosans_light
import docta.composeapp.generated.resources.notosans_medium
import docta.composeapp.generated.resources.notosans_regular
import docta.composeapp.generated.resources.notosans_semibold
import org.jetbrains.compose.resources.Font

val NotoSans: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.notosans_extralight, weight = FontWeight.ExtraLight),
        Font(Res.font.notosans_light, weight = FontWeight.Light),
        Font(Res.font.notosans_regular, weight = FontWeight.Normal),
        Font(Res.font.notosans_medium, weight = FontWeight.Medium),
        Font(Res.font.notosans_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.notosans_bold, weight = FontWeight.Bold),
        Font(Res.font.notosans_extrabold, weight = FontWeight.ExtraBold),
        Font(Res.font.notosans_black, weight = FontWeight.Black)
    )
