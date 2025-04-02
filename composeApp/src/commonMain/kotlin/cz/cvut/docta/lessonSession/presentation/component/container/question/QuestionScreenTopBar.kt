package cz.cvut.docta.lessonSession.presentation.component.container.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.IconButtonWithPopupContent
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.NotoSans
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.presentation.component.MaterialsPopupContentComponent
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.show_icon

@Composable
fun QuestionScreenTopBar(
    questionInstructions: String,
    materials: List<Materials>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
    ) {
        Text(
            text = questionInstructions,
            color = DoctaColors.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            fontFamily = NotoSans,
            letterSpacing = 0.1.sp,
            modifier = Modifier.weight(1f)
        )
        IconButtonWithPopupContent(
            iconRes = Res.drawable.show_icon,
            animationTransformOrigin = TransformOrigin(1f, 0f),
            alignment = Alignment.TopEnd,
            contentPadding = PaddingValues(0.dp),
            backgroundColor = Color.Transparent,
        ) {
            MaterialsPopupContentComponent(materials = materials)
        }
    }
}