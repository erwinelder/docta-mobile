package cz.cvut.docta.auth.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.domain.model.UserRole
import cz.cvut.docta.auth.presentation.model.ProfilePermissions
import cz.cvut.docta.auth.presentation.model.DataEditingState
import cz.cvut.docta.core.domain.app.AppTheme
import cz.cvut.docta.core.presentation.preview.ScreenPreviewContainer
import cz.cvut.docta.errorHandling.presentation.model.ValidatedFieldUiState

@Preview(device = Devices.PIXEL_7_PRO)
@Composable
fun ProfileScreenPreview(
    appTheme: AppTheme = AppTheme.Light,
    userData: UserData = UserData(
        id = 0,
        email = "example@fel.cvut.cz",
        role = UserRole.User,
        name = "User name"
    )
) {
    ScreenPreviewContainer(appTheme = appTheme) {
        ProfileScreen(
            permissions = ProfilePermissions(
                allowDeleteAccount = true,
                allowSignOut = true,
                allowChangeName = true,
                allowChangeRole = true
            ),
            onNavigateToDeleteAccountScreen = {},
            onNavigateToSignOutScreen = {},
            userData = userData,

            nameEditingState = DataEditingState.Idle,
            onToggleNameEditingState = {},
            onSaveName = {},
            nameState = ValidatedFieldUiState(fieldText = userData.name),
            onNameChange = {},

            roleEditingState = DataEditingState.Idle,
            onToggleRoleEditingState = {},
            onSaveRole = {},
            roleState = userData.role,
            availableRoles = UserRole.entries,
            onRoleSelect = {},

            userDataRequestState = null,
            onCancelUserDataRequest = {},
            onUserDataFetchResult = {}
        )
    }
}