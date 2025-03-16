package cz.cvut.docta.di

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.data.repository.AuthRepositoryImpl
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.usecase.CheckEmailVerificationUseCase
import cz.cvut.docta.auth.domain.usecase.CheckEmailVerificationUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCase
import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SignInUseCase
import cz.cvut.docta.auth.domain.usecase.SignInUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SignUpUseCase
import cz.cvut.docta.auth.domain.usecase.SignUpUseCaseImpl
import cz.cvut.docta.auth.presentation.viewmodel.SignInViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    /* ---------- Repositories ---------- */

    single<AuthRepository> {
        AuthRepositoryImpl()
    }

    /* ---------- Use Cases ---------- */

    single<SignInUseCase> {
        SignInUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<SignUpUseCase> {
        SignUpUseCaseImpl(authRepository = get())
    }

    single<CheckEmailVerificationUseCase> {
        CheckEmailVerificationUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<SaveUserDataToSecureStorageUseCase> {
        SaveUserDataToSecureStorageUseCaseImpl(secureStorage = get())
    }

    single<GetUserDataFromSecureStorageUseCase> {
        GetUserDataFromSecureStorageUseCaseImpl(secureStorage = get())
    }

    single<GetAuthTokenFromEncStoreUseCase> {
        GetAuthTokenFromEncStoreUseCaseImpl(secureStorage = get())
    }

    /* ---------- View Models ---------- */

    viewModel { parameters ->
        SignInViewModel(
            email = parameters.get<String>(),
            signInUseCase = get()
        )
    }

    viewModel { parameters ->
        SignUpViewModel(
            email = parameters.get<String>(),
            signUpUseCase = get(),
            checkEmailVerificationUseCase = get()
        )
    }

    /* ---------- Other ---------- */

    single {
        UserContext(
            saveUserDataToSecureStorageUseCase = get(),
            getUserDataFromSecureStorageUseCase = get(),
            getAuthTokenFromEncStoreUseCase = get()
        )
    }

}