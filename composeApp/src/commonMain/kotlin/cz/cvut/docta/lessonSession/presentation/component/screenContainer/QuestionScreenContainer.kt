package cz.cvut.docta.lessonSession.presentation.component.screenContainer

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.button.IconButtonWithPopupContent
import cz.cvut.docta.core.presentation.component.button.PrimaryButton
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.utils.getImeBottomInset
import cz.cvut.docta.lessonSession.presentation.component.MaterialsModal
import cz.cvut.docta.lessonSession.presentation.component.text.QuestionInstructionsTitle
import cz.cvut.docta.lessonSession.presentation.component.text.QuestionInstructionsTitleWithMaterials
import cz.cvut.docta.lessonSession.presentation.model.question.Materials
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.show_icon

@Composable
fun QuestionScreenContainer(
    screenPadding: PaddingValues,
    questionInstructions: String,
    questionMaterials: List<Materials>,
    buttonIsEnabled: Boolean,
    showCheckButton: Boolean,
    onCheckButtonClick: () -> Unit = {},
    onContinueButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val imeBottomInset by getImeBottomInset()
    val bottomPadding by animateDpAsState(imeBottomInset.coerceAtLeast(24.dp))

    ScreenContainer(
        screenPadding = screenPadding,
        padding = PaddingValues(top = 24.dp, bottom = bottomPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.clickable { focusManager.clearFocus() }
    ) {
        if( questionMaterials.isEmpty() ) {
            QuestionInstructionsTitle( questionInstructions = questionInstructions )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuestionInstructionsTitleWithMaterials(
                    questionInstructions = questionInstructions,
                    modifier = Modifier.weight(1f)
                )
                Spacer( modifier = Modifier.width(8.dp))
                IconButtonWithPopupContent(
                    iconRes = Res.drawable.show_icon,
                    popupContent = {
                        MaterialsModal( materials = questionMaterials )
                    }
                )
            }
        }
        content()
        PrimaryButton(
            text = stringResource(
                if (showCheckButton) SharedRes.strings.check else SharedRes.strings.continue_
            ),
            enabled = buttonIsEnabled,
            onClick = if (showCheckButton) onCheckButtonClick else
                onContinueButtonClick
        )
    }
}
