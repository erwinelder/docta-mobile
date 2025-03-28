package cz.cvut.docta.auth.data.repository

import cz.cvut.docta.auth.data.model.SignUpFormDto
import cz.cvut.docta.auth.data.model.UserCredentialsDto
import cz.cvut.docta.auth.data.model.UserDataDto
import cz.cvut.docta.auth.data.model.UserDataWithTokenDto
import cz.cvut.docta.auth.data.model.UserRoleDto
import cz.cvut.docta.core.data.remote.doctaBackendUrl
import cz.cvut.docta.core.data.remote.httpClient
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.AuthSuccess
import cz.cvut.docta.errorHandling.domain.model.result.Result
import cz.cvut.docta.errorHandling.domain.model.result.ResultData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class AuthRepositoryImpl() : AuthRepository {

    override suspend fun checkTokenValidity(token: String): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/auth/check-token-validity"
            ) {
                header("Authorization", "Bearer $token")
            }

            when (response.status) {
                HttpStatusCode.OK -> Result.Success(AuthSuccess.SignedIn)
                HttpStatusCode.Unauthorized -> Result.Error(AuthError.WrongCredentials)
                else -> Result.Error(AuthError.SignInError)
            }
        } catch (_: Exception) {
            Result.Error(error = AuthError.SignInError)
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): ResultData<UserDataWithTokenDto, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/auth/sign-in"
            ) {
                contentType(ContentType.Application.Json)
                setBody(UserCredentialsDto(email = email, password = password))
            }

            when (response.status) {
                HttpStatusCode.OK -> {
                    val userData = Json.decodeFromString<UserDataWithTokenDto>(string = response.bodyAsText())
                    ResultData.Success(userData)
                }
                HttpStatusCode.Unauthorized -> ResultData.Error(AuthError.WrongCredentials)
                else -> ResultData.Error(AuthError.SignInError)
            }
        } catch (_: Exception) {
            ResultData.Error(AuthError.SignInError)
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/auth/sign-up"
            ) {
                contentType(ContentType.Application.Json)
                setBody(SignUpFormDto(name = name, email = email, password = password))
            }

            when (response.status) {
                HttpStatusCode.Accepted -> Result.Success(AuthSuccess.EmailVerificationSent)
                HttpStatusCode.ServiceUnavailable -> Result.Error(AuthError.EmailVerificationError)
                HttpStatusCode.Conflict -> Result.Error(AuthError.UserAlreadyExists)
                else -> Result.Error(AuthError.SignUpError)
            }
        } catch (_: Exception) {
            Result.Error(AuthError.SignUpError)
        }
    }

    override suspend fun checkEmailVerification(
        name: String,
        email: String,
        password: String
    ): ResultData<UserDataWithTokenDto, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/auth/check-email-verification"
            ) {
                contentType(ContentType.Application.Json)
                setBody(SignUpFormDto(name = name, email = email, password = password))
            }

            when (response.status) {
                HttpStatusCode.OK -> {
                    val userData = Json.decodeFromString<UserDataWithTokenDto>(string = response.bodyAsText())
                    ResultData.Success(data = userData)
                }
                HttpStatusCode.Unauthorized -> ResultData.Error(AuthError.EmailNotVerifiedError)
                else -> ResultData.Error(AuthError.EmailVerificationError)
            }
        } catch (_: Exception) {
            ResultData.Error(AuthError.EmailVerificationError)
        }
    }

    override suspend fun getUserData(userId: Int, token: String): ResultData<UserDataDto, AuthError> {
        return try {
            val response = httpClient.get(
                urlString = "$doctaBackendUrl/account/$userId"
            ) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
            }

            when (response.status) {
                HttpStatusCode.OK -> {
                    val userData = Json.decodeFromString<UserDataDto>(string = response.bodyAsText())
                    ResultData.Success(data = userData)
                }
                HttpStatusCode.NotFound -> ResultData.Error(AuthError.UserNotFound)
                else -> ResultData.Error(AuthError.UserDataFetchError)
            }
        } catch (_: Exception) {
            ResultData.Error(AuthError.UserDataFetchError)
        }
    }

    override suspend fun saveUserName(userId: Int, name: String, token: String): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/account/$userId/save/name/$name"
            ) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
            }

            when (response.status) {
                HttpStatusCode.Created -> Result.Success(AuthSuccess.NameUpdated)
                else -> Result.Error(AuthError.NameUpdateError)
            }
        } catch (_: Exception) {
            Result.Error(AuthError.NameUpdateError)
        }
    }

    override suspend fun saveUserRole(
        userId: Int,
        role: UserRoleDto,
        token: String
    ): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/account/$userId/save/role/${role.name}"
            ) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
            }

            when (response.status) {
                HttpStatusCode.Created -> Result.Success(AuthSuccess.RoleUpdated)
                else -> Result.Error(AuthError.RoleUpdateError)
            }
        } catch (_: Exception) {
            Result.Error(AuthError.RoleUpdateError)
        }
    }

    override suspend fun deleteOwnAccount(
        userId: Int,
        token: String,
        email: String,
        password: String
    ): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/account/$userId/delete"
            ) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
                setBody(UserCredentialsDto(email = email, password = password))
            }

            when (response.status) {
                HttpStatusCode.NoContent -> Result.Success(AuthSuccess.OwnAccountDeleted)
                HttpStatusCode.Unauthorized -> Result.Error(AuthError.WrongCredentials)
                else -> Result.Error(AuthError.AccountDeletionError)
            }
        } catch (_: Exception) {
            Result.Error(AuthError.AccountDeletionError)
        }
    }

    override suspend fun deleteUserAccount(
        userId: Int,
        token: String
    ): Result<AuthSuccess, AuthError> {
        return try {
            val response = httpClient.post(
                urlString = "$doctaBackendUrl/account/$userId/delete"
            ) {
                header("Authorization", "Bearer $token")
                contentType(ContentType.Application.Json)
            }

            when (response.status) {
                HttpStatusCode.NoContent -> Result.Success(AuthSuccess.UserAccountDeleted)
                else -> Result.Error(AuthError.AccountDeletionError)
            }
        } catch (_: Exception) {
            Result.Error(AuthError.AccountDeletionError)
        }
    }

}