package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.mapper.toDomainModel
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

class CheckEmailVerificationUseCaseImpl(
    private val authRepository: AuthRepository
) : CheckEmailVerificationUseCase {
    override suspend fun execute(
        name: String,
        email: String,
        password: String
    ): ResultData<UserData, AuthError> {
        return authRepository
            .checkEmailVerification(
                name = name,
                email = email,
                password = password
            )
            .mapData(UserDataDto::toDomainModel)
    }
}