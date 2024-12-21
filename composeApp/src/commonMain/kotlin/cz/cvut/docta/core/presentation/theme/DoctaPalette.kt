package cz.cvut.docta.core.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class DoctaPalette(
    val primary: Color,
    val onPrimary: Color,
    val surface: Color,
    val onSurface: Color,
    val background: Color,
    val onBackground: Color,
    val glassSurfaceGradient: List<Color>,
    val glassSurfaceBorder: Color,
    val glassSurfaceLightAndDarkShadow: Pair<Color, Color>
) {
    data object Light : DoctaPalette(
        primary = Color(0xFF6200EE),
        onPrimary = Color.White,
        surface = Color(247, 247, 247),
        onSurface = Color(8, 8, 8),
        background = Color.White,
        onBackground = Color.Black,
        glassSurfaceGradient = listOf(
            Color(242, 242, 242, 128),
            Color(252, 252, 252, 128)
        ),
        glassSurfaceBorder = Color(255, 255, 255, 69),
        glassSurfaceLightAndDarkShadow = Pair(
            Color(255, 255, 255, 120),
            Color(201, 201, 201, 120)
        )
    )
    data object Dark : DoctaPalette(
        primary = Color(0xFFBB86FC),
        onPrimary = Color.Black,
        surface = Color(33, 33, 33),
        onSurface = Color(247, 247, 247),
        background = Color.Black,
        onBackground = Color.White,
        glassSurfaceGradient = listOf(
            Color(23, 23, 23, 128),
            Color(31, 31, 31, 128)
        ),
        glassSurfaceBorder = Color(35, 35, 35, 128),
        glassSurfaceLightAndDarkShadow = Pair(
            Color(39, 39, 39, 120),
            Color(19, 19, 19, 120)
        )
    )
}