package cz.cvut.docta.auth.data.repository

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

interface AuthRepository {

    suspend fun signIn(email: String, password: String): ResultData<UserDataDto, AuthError>

    suspend fun signUp(name: String, email: String, password: String): Result<AuthSuccess, AuthError>

    suspend fun checkEmailVerification(name: String, email: String, password: String): ResultData<UserDataDto, AuthError>

}