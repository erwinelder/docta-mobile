package cz.cvut.docta.auth.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import cz.cvut.docta.auth.domain.model.UserContext

@Stable
@Immutable
data class ProfilePermissions(
    val allowDeleteAccount: Boolean,
    val allowSignOut: Boolean,
    val allowChangeName: Boolean,
    val allowChangeRole: Boolean
) {

    companion object {

        fun fromViewerAndProfileUserId(
            viewerContext: UserContext,
            profileUserId: Int
        ): ProfilePermissions {
            return ProfilePermissions(
                allowDeleteAccount = viewerContext.isOwner() || viewerContext.userId == profileUserId,
                allowSignOut = viewerContext.userId == profileUserId,
                allowChangeName = viewerContext.isOwner() || viewerContext.userId == profileUserId,
                allowChangeRole = viewerContext.isOwner() && viewerContext.userId != profileUserId
            )
        }

    }

}