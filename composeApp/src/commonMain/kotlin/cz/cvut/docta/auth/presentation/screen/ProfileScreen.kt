package cz.cvut.docta.auth.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.auth.presentation.component.EditableDataComponent
import cz.cvut.docta.auth.presentation.model.DataEditingState
import cz.cvut.docta.auth.presentation.model.ProfilePermissions
import cz.cvut.docta.auth.presentation.utils.asStringRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.button.SmallSecondaryButton
import cz.cvut.docta.core.presentation.component.field.SmallTextField
import cz.cvut.docta.core.presentation.component.picker.PopupPicker
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
import docta.composeapp.generated.resources.delete_account_icon
import docta.composeapp.generated.resources.sign_out_icon

@Composable
fun ProfileScreen(
    screenPadding: PaddingValues = PaddingValues(0.dp),
    permissions: ProfilePermissions,
    onNavigateToDeleteAccountScreen: () -> Unit,
    onNavigateToSignOutScreen: () -> Unit,
    userData: UserData,

    nameEditingState: DataEditingState,
    onToggleNameEditingState: () -> Unit,
    onSaveName: () -> Unit,
    nameState: ValidatedFieldUiState,
    onNameChange: (String) -> Unit,

    roleEditingState: DataEditingState,
    onToggleRoleEditingState: () -> Unit,
    onSaveRole: () -> Unit,
    roleState: UserRole,
    availableRoles: List<UserRole>,
    onRoleSelect: (UserRole) -> Unit,

    userDataRequestState: RequestState?,
    onCancelUserDataRequest: () -> Unit,
    onUserDataFetchResult: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    ScreenWithRequestState(
        screenPadding = screenPadding,
        requestState = userDataRequestState,
        onCancelRequest = onCancelUserDataRequest,
        onErrorClose = onUserDataFetchResult
    ) {
        ScreenContainer(
            verticalArrangement = Arrangement.spacedBy(48.dp),
            modifier = Modifier.clickable { focusManager.clearFocus() }
        ) {
            ButtonsBlock(
                permissions = permissions,
                onNavigateToDeleteAccountScreen = onNavigateToDeleteAccountScreen,
                onNavigateToSignOutScreen = onNavigateToSignOutScreen
            )
            UserDataBlockComponent(
                permissions = permissions,
                userData = userData,

                nameEditingState = nameEditingState,
                onToggleNameEditingState = onToggleNameEditingState,
                onSaveName = onSaveName,
                nameState = nameState,
                onNameChange = onNameChange,

                roleEditingState = roleEditingState,
                onToggleRoleEditingState = onToggleRoleEditingState,
                onSaveRole = onSaveRole,
                roleState = roleState,
                availableRoles = availableRoles,
                onRoleSelect = onRoleSelect
            )
        }
    }
}

@Composable
private fun ButtonsBlock(
    permissions: ProfilePermissions,
    onNavigateToDeleteAccountScreen: () -> Unit,
    onNavigateToSignOutScreen: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        if (permissions.allowDeleteAccount) {
            SmallSecondaryButton(
                text = stringResource(SharedRes.strings.delete_account),
                iconRes = Res.drawable.delete_account_icon,
                enabledGradientColor = DoctaColors.dangerousGlassGradientPair,
                alpha = 1f,
                contentColor = DoctaColors.onPrimary,
                borderColor = DoctaColors.semiTransparentBorder,
                onClick = onNavigateToDeleteAccountScreen
            )
        }
        if (permissions.allowSignOut) {
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
    permissions: ProfilePermissions,
    userData: UserData,

    nameEditingState: DataEditingState,
    onToggleNameEditingState: () -> Unit,
    onSaveName: () -> Unit,
    nameState: ValidatedFieldUiState,
    onNameChange: (String) -> Unit,

    roleEditingState: DataEditingState,
    onToggleRoleEditingState: () -> Unit,
    onSaveRole: () -> Unit,
    roleState: UserRole,
    availableRoles: List<UserRole>,
    onRoleSelect: (UserRole) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
    ) {
        EditableDataComponent(
            dataEditingState = nameEditingState,
            allowEditing = permissions.allowChangeName,
            onToggleDataEditingState = onToggleNameEditingState,
            idleStateComponent = {
                PrimaryProfileDataTextComponent(text = userData.name)
            },
            editingStateComponent = {
                SmallTextField(
                    text = nameState.fieldText,
                    onValueChange = onNameChange,
                    fontSize = 20.sp,
                    imeAction = ImeAction.Go,
                    onGoKeyboardAction = { if (nameState.isValid()) onSaveName() }
                )
            },
            allowSaving = nameState.isValid(),
            onSaveData = onSaveName,
            bottomComponent = {
                AnimatedContent(
                    targetState = nameState.validationStates,
                    transitionSpec = {
                        (fadeIn()).togetherWith(fadeOut())
                    }
                ) { validationStates ->
                    FieldValidationMessages(validationStates = validationStates)
                }
            }
        )
        EditableDataComponent(
            dataEditingState = roleEditingState,
            allowEditing = permissions.allowChangeRole,
            onToggleDataEditingState = onToggleRoleEditingState,
            idleStateComponent = {
                SecondaryProfileDataTextComponent(text = stringResource(userData.role.asStringRes()))
            },
            editingStateComponent = {
                PopupPicker(
                    selectedItem = roleState,
                    itemList = availableRoles,
                    itemToStringMapper = { stringResource(it.asStringRes()) },
                    onItemSelect = onRoleSelect
                )
            },
            onSaveData = onSaveRole
        )
        SecondaryProfileDataTextComponent(text = userData.email)
    }
}

@Composable
private fun PrimaryProfileDataTextComponent(text: String) {
    Text(
        text = text,
        color = DoctaColors.onSurface,
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        fontFamily = Manrope
    )
}

@Composable
private fun SecondaryProfileDataTextComponent(text: String) {
    Text(
        text = text,
        color = DoctaColors.onSurface,
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        fontFamily = Manrope
    )
}
