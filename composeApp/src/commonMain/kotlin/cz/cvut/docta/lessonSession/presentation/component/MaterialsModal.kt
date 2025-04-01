package cz.cvut.docta.lessonSession.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.NotoSans
import cz.cvut.docta.lessonSession.domain.model.Materials

@Composable
fun MaterialsModal(
    materials: List<Materials>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Text(
            text = "Materials",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            fontFamily = NotoSans,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        materials.forEachIndexed { index, material ->
            Text(
                text = material.text
            )

            if (index != materials.lastIndex) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    color = DoctaColors.primary.copy(alpha = 0.8f),
                    thickness = 2.dp
                )
            }
        }
    }
}
