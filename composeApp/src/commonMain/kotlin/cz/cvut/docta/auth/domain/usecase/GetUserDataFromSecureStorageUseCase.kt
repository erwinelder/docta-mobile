package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserData

interface GetUserDataFromSecureStorageUseCase {
    fun execute(): UserData
}