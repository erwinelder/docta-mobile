package cz.cvut.docta.auth.data.repository

import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.model.UserDataWithTokenDto
import cz.cvut.docta.auth.data.model.UserRoleDto
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

interface AuthRepository {

    suspend fun checkTokenValidity(token: String): Result<AuthSuccess, AuthError>

    suspend fun signIn(email: String, password: String): ResultData<UserDataWithTokenDto, AuthError>

    suspend fun signUp(name: String, email: String, password: String): Result<AuthSuccess, AuthError>

    suspend fun checkEmailVerification(name: String, email: String, password: String): ResultData<UserDataWithTokenDto, AuthError>

    suspend fun getUserData(userId: Int, token: String): ResultData<UserDataDto, AuthError>

    suspend fun saveUserName(userId: Int, name: String, token: String): Result<AuthSuccess, AuthError>

    suspend fun saveUserRole(userId: Int, role: UserRoleDto, token: String): Result<AuthSuccess, AuthError>

}