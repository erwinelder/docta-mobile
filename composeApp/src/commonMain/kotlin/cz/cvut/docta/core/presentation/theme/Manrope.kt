package cz.cvut.docta.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.manrope_bold
import docta.composeapp.generated.resources.manrope_extrabold
import docta.composeapp.generated.resources.manrope_extralight
import docta.composeapp.generated.resources.manrope_light
import docta.composeapp.generated.resources.manrope_medium
import docta.composeapp.generated.resources.manrope_regular
import docta.composeapp.generated.resources.manrope_semibold
import org.jetbrains.compose.resources.Font

val Manrope: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.manrope_extralight, weight = FontWeight.ExtraLight),
        Font(Res.font.manrope_light, weight = FontWeight.Light),
        Font(Res.font.manrope_regular, weight = FontWeight.Normal),
        Font(Res.font.manrope_medium, weight = FontWeight.Medium),
        Font(Res.font.manrope_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.manrope_bold, weight = FontWeight.Bold),
        Font(Res.font.manrope_extrabold, weight = FontWeight.ExtraBold)
    )
