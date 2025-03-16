package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.mapper.toDomainModel
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result

class SignInUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : SignInUseCase {
    override suspend fun execute(
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError> {
        val result = authRepository
            .signIn(email = email, password = password)
            .mapData(UserDataDto::toDomainModel)

        result.getDataIfSuccess()?.let {
            userContext.saveUserData(userDataWithToken = it)
        }

        return result.toDefaultResult(success = AuthSuccess.SignedIn)
    }
}