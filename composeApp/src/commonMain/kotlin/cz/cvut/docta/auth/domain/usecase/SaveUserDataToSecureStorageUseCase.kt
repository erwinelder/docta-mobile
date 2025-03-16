package cz.cvut.docta.auth.domain.usecase

import cz.cvut.docta.auth.domain.model.UserDataWithToken

interface SaveUserDataToSecureStorageUseCase {
    fun execute(userDataWithToken: UserDataWithToken)
}