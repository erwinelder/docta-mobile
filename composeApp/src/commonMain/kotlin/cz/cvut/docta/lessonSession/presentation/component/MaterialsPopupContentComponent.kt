package cz.cvut.docta.lessonSession.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.divider.SmallDivider
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.Materials

@Composable
fun MaterialsPopupContentComponent(
    materials: List<Materials>
) {
    val listLastIndex by remember { derivedStateOf { materials.lastIndex } }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 20.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(26.dp))
                .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
                .background(DoctaColors.surface)
        ) {
            itemsIndexed(items = materials) { index, materialsItem ->
                Text(
                    text = materialsItem.text,
                    color = DoctaColors.onSurface,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = Manrope
                )
                if (index != listLastIndex) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SmallDivider(
                            modifier = Modifier.padding(top = 12.dp),
                            filledWidth = .5f
                        )
                    }
                }
            }
        }
    }
}
