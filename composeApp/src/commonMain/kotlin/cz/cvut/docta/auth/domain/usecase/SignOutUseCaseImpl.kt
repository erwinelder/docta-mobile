package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserContext

class SignOutUseCaseImpl(
    private val userContext: UserContext
) : SignOutUseCase {
    override suspend fun execute() {
        userContext.resetUserData()
    }
}