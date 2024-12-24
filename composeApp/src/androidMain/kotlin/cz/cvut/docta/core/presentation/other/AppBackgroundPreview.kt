package cz.cvut.docta.core.presentation.other

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
import androidx.compose.ui.res.painterResource
import cz.cvut.docta.R
import cz.cvut.docta.core.domain.app.AppTheme

@Composable
fun AppBackgroundPreview(appTheme: AppTheme?) {
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
                        AppTheme.Light -> R.drawable.main_background_light
                        AppTheme.Dark -> R.drawable.main_background_dark
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } ?: Box(modifier = Modifier.fillMaxSize())
    }
}