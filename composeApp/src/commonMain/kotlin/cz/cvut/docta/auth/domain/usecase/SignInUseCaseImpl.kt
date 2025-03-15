package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.mapper.toDomainModel
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

class SignInUseCaseImpl(
    private val authRepository: AuthRepository
) : SignInUseCase {
    override suspend fun execute(
        email: String,
        password: String
    ): ResultData<UserData, AuthError> {
        return authRepository
            .signIn(email = email, password = password)
            .mapData(UserDataDto::toDomainModel)
    }
}