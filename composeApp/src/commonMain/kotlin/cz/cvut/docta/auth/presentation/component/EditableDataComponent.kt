package cz.cvut.docta.auth.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.presentation.model.DataEditingState
import cz.cvut.docta.core.presentation.component.button.AnimatedSecondaryIconButton
import cz.cvut.docta.core.presentation.component.button.SecondaryIconButton
import cz.cvut.docta.core.presentation.component.loader.SmallLoaderComponent
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.checked_icon
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.edit_icon

@Composable
fun EditableDataComponent(
    dataEditingState: DataEditingState,
    allowEditing: Boolean = false,
    onToggleDataEditingState: () -> Unit,
    allowSaving: Boolean = true,
    onSaveData: () -> Unit,
    idleStateComponent: @Composable () -> Unit,
    editingStateComponent: @Composable () -> Unit,
    bottomComponent: @Composable (() -> Unit)? = null
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (allowEditing) {
                ModifyDataButton(
                    visible = dataEditingState !is DataEditingState.Saving,
                    dataEditingState = dataEditingState,
                    onToggleDataEditingState = onToggleDataEditingState
                )
            }
            AnimatedContent(
                targetState = dataEditingState
            ) { editingState ->
                when (editingState) {
                    is DataEditingState.Idle -> idleStateComponent()
                    is DataEditingState.Editing -> editingStateComponent()
                    is DataEditingState.Saving -> SmallLoaderComponent(
                        text = stringResource(SharedRes.strings.saving_loader)
                    )
                }
            }
            AnimatedVisibility(
                visible = dataEditingState is DataEditingState.Editing && allowSaving
            ) {
                SecondaryIconButton(
                    iconRes = Res.drawable.checked_icon,
                    iconDescription = "Save icon",
                    onClick = onSaveData
                )
            }
        }
        bottomComponent?.invoke()
    }
}

@Composable
private fun ModifyDataButton(
    visible: Boolean,
    dataEditingState: DataEditingState,
    onToggleDataEditingState: () -> Unit
) {
    Row {
        Spacer(modifier = Modifier.height(44.dp))
        AnimatedVisibility(
            visible = visible
        ) {
            AnimatedSecondaryIconButton(
                iconState = dataEditingState,
                onClick = onToggleDataEditingState,
                iconResProvider = {
                    if (it is DataEditingState.Idle) Res.drawable.edit_icon
                    else Res.drawable.close_icon
                },
                iconDescriptionProvider = {
                    if (it is DataEditingState.Idle) "Edit icon"
                    else "Save icon"
                }
            )
        }
    }
}