package cz.cvut.docta.core.presentation.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.PreviewContainer
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.search_icon

@Preview(device = PIXEL_7_PRO)
@Composable
fun ButtonWithPopupContentPreview(
    appTheme: AppTheme = AppTheme.Light,
) {
    PreviewContainer(appTheme = appTheme) {
        ButtonWithPopupContent(
            buttonText = stringResource(SharedRes.strings.check),
            iconRes = Res.drawable.search_icon,
            animationTransformOrigin = TransformOrigin(.7f, 0f)
        ) {

        }
    }
}