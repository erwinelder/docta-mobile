package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

interface CheckEmailVerificationUseCase {
    suspend fun execute(
        name: String,
        email: String,
        password: String
    ): ResultData<UserData, AuthError>
}