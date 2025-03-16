package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.mapper.toDomainModel
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class CheckEmailVerificationUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : CheckEmailVerificationUseCase {
    override suspend fun execute(
        name: String,
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError> {
        val result = authRepository
            .checkEmailVerification(
                name = name,
                email = email,
                password = password
            )
            .mapData(UserDataDto::toDomainModel)

        result.getDataIfSuccess()?.let {
            userContext.saveUserData(userDataWithToken = it)
        }

        return result.toDefaultResult(success = AuthSuccess.SignedUp)
    }
}