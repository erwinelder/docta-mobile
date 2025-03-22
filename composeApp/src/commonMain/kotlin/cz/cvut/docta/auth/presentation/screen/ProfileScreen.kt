package cz.cvut.docta.auth.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.presentation.model.UserNameEditingState
import cz.cvut.docta.auth.presentation.utils.asStringRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.buttons.AnimatedSecondaryIconButton
import cz.cvut.docta.core.presentation.component.buttons.SecondaryIconButton
import cz.cvut.docta.core.presentation.component.buttons.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.field.SmallTextField
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.errorHandling.presentation.component.container.ScreenWithRequestState
import cz.cvut.docta.errorHandling.presentation.component.field.FieldValidationMessages
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.checked_icon
import docta.composeapp.generated.resources.close_icon
import docta.composeapp.generated.resources.delete_account_icon
import docta.composeapp.generated.resources.edit_icon
import docta.composeapp.generated.resources.short_arrow_left_icon
import docta.composeapp.generated.resources.sign_out_icon

@Composable
fun ProfileScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    onNavigateToDeleteAccountScreen: () -> Unit,
    onNavigateToSignOutScreen: (() -> Unit)?,
    userData: UserData,
    nameState: ValidatedFieldUiState,
    userNameEditingState: UserNameEditingState,
    onToggleUserNameEditingState: () -> Unit,
    onNameChange: (String) -> Unit,
    onSaveName: () -> Unit,
    requestState: RequestState?,
    onCancelRequest: () -> Unit,
    onCloseResult: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    ScreenWithRequestState(
        screenPadding = screenPadding,
        requestState = requestState,
        closeButtonText = stringResource(SharedRes.strings.back),
        closeButtonIconRes = Res.drawable.short_arrow_left_icon,
        onCancelRequest = onCancelRequest,
        onCloseResult = onCloseResult
    ) {
        ScreenContainer(
            verticalArrangement = Arrangement.spacedBy(48.dp),
            screenPadding = screenPadding,
            modifier = Modifier.clickable { focusManager.clearFocus() }
        ) {
            ButtonsBlock(
                onNavigateToDeleteAccountScreen = onNavigateToDeleteAccountScreen,
                onNavigateToSignOutScreen = onNavigateToSignOutScreen
            )
            UserDataBlockComponent(
                userData = userData,
                nameState = nameState,
                userNameEditingState = userNameEditingState,
                onToggleUserNameEditingState = onToggleUserNameEditingState,
                onNameChange = onNameChange,
                onSaveName = onSaveName
            )
        }
    }
}

@Composable
private fun ButtonsBlock(
    onNavigateToDeleteAccountScreen: () -> Unit,
    onNavigateToSignOutScreen: (() -> Unit)?
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        SmallSecondaryButton(
            text = stringResource(SharedRes.strings.delete_account),
            iconRes = Res.drawable.delete_account_icon,
            enabledGradientColor = DoctaColors.dangerousGlassGradientPair,
            contentColor = DoctaColors.onPrimary,
            borderColor = DoctaColors.semiTransparentBorder,
            onClick = onNavigateToDeleteAccountScreen
        )
        onNavigateToSignOutScreen?.let {
            SmallSecondaryButton(
                text = stringResource(SharedRes.strings.sign_out),
                iconRes = Res.drawable.sign_out_icon,
                onClick = onNavigateToSignOutScreen
            )
        }
    }
}

@Composable
private fun UserDataBlockComponent(
    userData: UserData,
    nameState: ValidatedFieldUiState,
    userNameEditingState: UserNameEditingState,
    onToggleUserNameEditingState: () -> Unit,
    onNameChange: (String) -> Unit,
    onSaveName: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
    ) {
        EditableNameComponent(
            nameState = nameState,
            userNameEditingState = userNameEditingState,
            onToggleUserNameEditingState = onToggleUserNameEditingState,
            onNameChange = onNameChange,
            onSaveName = onSaveName
        )
        Text(
            text = userData.email,
            color = DoctaColors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            fontFamily = Manrope
        )
        Text(
            text = stringResource(userData.role.asStringRes()),
            color = DoctaColors.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            fontFamily = Manrope
        )
    }
}

@Composable
private fun EditableNameComponent(
    nameState: ValidatedFieldUiState,
    userNameEditingState: UserNameEditingState,
    onToggleUserNameEditingState: () -> Unit,
    onNameChange: (String) -> Unit,
    onSaveName: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Spacer(modifier = Modifier.height(44.dp))
                AnimatedVisibility(
                    visible = userNameEditingState !is UserNameEditingState.Saving
                ) {
                    AnimatedSecondaryIconButton(
                        iconState = userNameEditingState,
                        onClick = onToggleUserNameEditingState,
                        iconResProvider = {
                            if (it is UserNameEditingState.Idle) Res.drawable.edit_icon
                            else Res.drawable.close_icon
                        },
                        iconDescriptionProvider = {
                            if (it is UserNameEditingState.Idle) "Edit icon"
                            else "Save icon"
                        }
                    )
                }
            }
            AnimatedContent(
                targetState = userNameEditingState
            ) { editingState ->
                when (editingState) {
                    is UserNameEditingState.Idle -> {
                        Text(
                            text = editingState.name,
                            color = DoctaColors.onSurface,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W600,
                            fontFamily = Manrope
                        )
                    }
                    is UserNameEditingState.Editing -> {
                        SmallTextField(
                            text = nameState.fieldText,
                            onValueChange = onNameChange,
                            fontSize = 20.sp,
                            imeAction = ImeAction.Go,
                            onGoKeyboardAction = {
                                if (nameState.isValid()) onSaveName()
                            }
                        )
                    }
                    is UserNameEditingState.Saving -> {
                        Text(
                            text = stringResource(SharedRes.strings.saving),
                            color = DoctaColors.outline,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            fontFamily = Manrope
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = userNameEditingState is UserNameEditingState.Editing
                        && nameState.isValid()
            ) {
                SecondaryIconButton(
                    iconRes = Res.drawable.checked_icon,
                    iconDescription = "Save icon",
                    onClick = onSaveName
                )
            }
        }
        AnimatedContent(
            targetState = nameState.validationStates,
            transitionSpec = {
                (fadeIn()).togetherWith(fadeOut())
            }
        ) { validationStates ->
            FieldValidationMessages(validationStates = validationStates)
        }
    }
}