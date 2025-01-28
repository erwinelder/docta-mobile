package cz.cvut.docta.core.presentation.theme

import androidx.compose.ui.graphics.Color

sealed class DoctaPalette(
    val primary: Color,
    val primaryGradient: List<Color>,
    val primaryGradientPair: Pair<Color, Color> = Pair(primaryGradient[0], primaryGradient[1]),
    val primaryGlassGradient: List<Color>,
    val primaryGlassGradientPair: Pair<Color, Color> = Pair(
        primaryGlassGradient[0], primaryGlassGradient[1]
    ),
    val onPrimary: Color,

    val background: Color,
    val surface: Color,
    val onSurface: Color,

    val glassSurfaceGradient: List<Color>,
    val glassSurfaceGradientPair: Pair<Color, Color> = Pair(
        glassSurfaceGradient[0], glassSurfaceGradient[1]
    ),
    val glassSurfaceBorder: Color,

    val outline: Color,

    val semiTransparentBorder: Color,

    val disabledGradient: List<Color>,
    val disabledGradientPair: Pair<Color, Color> = Pair(disabledGradient[0], disabledGradient[1]),
    val disabledSemiTransparentGradient: List<Color>,
    val disabledSemiTransparentGradientPair: Pair<Color, Color> = Pair(
        disabledSemiTransparentGradient[0], disabledSemiTransparentGradient[1]
    ),

    val easyDifficultyColor: Color,
    val mediumDifficultyColor: Color,
    val hardDifficultyColor: Color,

    val successGlassGradient: List<Color>,
    val successGlassGradientPair: Pair<Color, Color> = Pair(
        successGlassGradient[0], successGlassGradient[1]
    ),
    val errorGlassGradient: List<Color>,
    val errorGlassGradientPair: Pair<Color, Color> = Pair(
        errorGlassGradient[0], errorGlassGradient[1]
    ),
) {
    data object Light : DoctaPalette(
        primary = Color(124, 154, 207),
        primaryGradient = listOf(
            Color(124, 154, 207),
            Color(124, 154, 207)
        ),
        primaryGlassGradient = listOf(
            Color(89, 131, 201, 51),
            Color(97, 140, 213, 51)
        ),
        onPrimary = Color(245, 245, 245),

        background = Color.White,
        surface = Color(247, 247, 247),
        onSurface = Color(8, 8, 8),

        glassSurfaceGradient = listOf(
            Color(242, 242, 242, 128),
            Color(252, 252, 252, 128)
        ),
        glassSurfaceBorder = Color(255, 255, 255, 69),

        outline = Color(8, 8, 8, 128),

        semiTransparentBorder = Color(255, 255, 255, 50),

        disabledGradient = listOf(
            Color(168, 168, 168),
            Color(164, 164, 164)
        ),
        disabledSemiTransparentGradient = listOf(
            Color(168, 168, 168, 64),
            Color(164, 164, 164, 64)
        ),

        easyDifficultyColor = Color(111, 171, 124),
        mediumDifficultyColor = Color(189, 164, 94),
        hardDifficultyColor = Color(187, 109, 109),

        successGlassGradient = listOf(
            Color(130, 211, 110, 128),
            Color(121, 200, 101, 128)
        ),
        errorGlassGradient = listOf(
            Color(229, 115, 115, 128),
            Color(219, 107, 107, 128)
        ),
    )
    data object Dark : DoctaPalette(
        primary = Color(77, 101, 143),
        primaryGradient = listOf(
            Color(73, 96, 136),
            Color(82, 106, 150)
        ),
        primaryGlassGradient = listOf(
            Color(124, 167, 241, 77),
            Color(131, 177, 255, 77)
        ),
        onPrimary = Color(245, 245, 245),

        background = Color.Black,
        surface = Color(33, 33, 33),
        onSurface = Color(237, 237, 237),

        glassSurfaceGradient = listOf(
            Color(35, 35, 35, 128),
            Color(41, 41, 41, 128)
        ),
        glassSurfaceBorder = Color(35, 35, 35, 128),

        outline = Color(247, 247, 247, 128),

        semiTransparentBorder = Color(0, 0, 0, 64),

        disabledGradient = listOf(
            Color(106, 106, 106),
            Color(110, 110, 110)
        ),
        disabledSemiTransparentGradient = listOf(
            Color(106, 106, 106, 64),
            Color(110, 110, 110, 64)
        ),

        easyDifficultyColor = Color(107, 154, 117),
        mediumDifficultyColor = Color(162, 141, 82),
        hardDifficultyColor = Color(162, 100, 100),

        successGlassGradient = listOf(
            Color(155, 233, 135, 128),
            Color(167, 244, 148, 128)
        ),
        errorGlassGradient = listOf(
            Color(250, 123, 123, 128),
            Color(255, 129, 129, 128)
        ),
    )
}