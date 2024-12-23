package cz.cvut.docta.core.presentation.component.other

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.theme.CurrAppTheme
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.main_background_dark
import docta.composeapp.generated.resources.main_background_light
import org.jetbrains.compose.resources.painterResource


@Composable
fun AppBackground(
    appTheme: AppTheme? = CurrAppTheme
) {
    AnimatedContent(
        targetState = appTheme,
        label = "App background",
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        }
    ) { targetAppTheme ->
        targetAppTheme?.let {
            Image(
                painter = painterResource(
                    when (it) {
                        AppTheme.Light -> Res.drawable.main_background_light
                        AppTheme.Dark -> Res.drawable.main_background_dark
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } ?: Box(modifier = Modifier.fillMaxSize())
    }
}