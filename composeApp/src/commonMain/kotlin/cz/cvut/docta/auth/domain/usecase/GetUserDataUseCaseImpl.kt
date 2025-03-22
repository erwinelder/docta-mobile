package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.model.UserData
import cz.cvut.docta.auth.mapper.toDomainModel
import cz.cvut.docta.errorHandling.domain.model.result.AuthError
import cz.cvut.docta.errorHandling.domain.model.result.ResultData

class GetUserDataUseCaseImpl(
    private val authRepository: AuthRepository,
    private val userContext: UserContext
) : GetUserDataUseCase {
    override suspend fun execute(userId: Int): ResultData<UserData, AuthError> {
        return authRepository
            .getUserData(userId = userId, token = userContext.getAuthToken())
            .mapData { it.toDomainModel() }
    }
}