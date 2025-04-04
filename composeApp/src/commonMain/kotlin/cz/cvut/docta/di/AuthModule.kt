package cz.cvut.docta.di

import cz.cvut.docta.auth.data.repository.AuthRepository
import cz.cvut.docta.auth.data.repository.AuthRepositoryImpl
import cz.cvut.docta.auth.domain.model.UserContext
import cz.cvut.docta.auth.domain.usecase.CheckAuthTokenValidityUseCase
import cz.cvut.docta.auth.domain.usecase.CheckAuthTokenValidityUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.CheckEmailVerificationUseCase
import cz.cvut.docta.auth.domain.usecase.CheckEmailVerificationUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.DeleteOwnAccountUseCase
import cz.cvut.docta.auth.domain.usecase.DeleteOwnAccountUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.DeleteUserAccountUseCase
import cz.cvut.docta.auth.domain.usecase.DeleteUserAccountUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCase
import cz.cvut.docta.auth.domain.usecase.GetAuthTokenFromEncStoreUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.GetUserDataFromSecureStorageUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.GetUserDataUseCase
import cz.cvut.docta.auth.domain.usecase.GetUserDataUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserDataToSecureStorageUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SaveUserNameToSecureStorageUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameToSecureStorageUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SaveUserNameUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserNameUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SaveUserRoleUseCase
import cz.cvut.docta.auth.domain.usecase.SaveUserRoleUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SignInUseCase
import cz.cvut.docta.auth.domain.usecase.SignInUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SignOutUseCase
import cz.cvut.docta.auth.domain.usecase.SignOutUseCaseImpl
import cz.cvut.docta.auth.domain.usecase.SignUpUseCase
import cz.cvut.docta.auth.domain.usecase.SignUpUseCaseImpl
import cz.cvut.docta.auth.presentation.viewmodel.DeleteOwnAccountViewModel
import cz.cvut.docta.auth.presentation.viewmodel.DeleteUserAccountViewModel
import cz.cvut.docta.auth.presentation.viewmodel.ProfileViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignInViewModel
import cz.cvut.docta.auth.presentation.viewmodel.SignOutViewModel
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

    single<CheckAuthTokenValidityUseCase> {
        CheckAuthTokenValidityUseCaseImpl(authRepository = get())
    }

    single<SaveUserDataToSecureStorageUseCase> {
        SaveUserDataToSecureStorageUseCaseImpl(secureStorage = get())
    }

    single<SaveUserNameToSecureStorageUseCase> {
        SaveUserNameToSecureStorageUseCaseImpl(
            secureStorage = get(),
            userContext = get()
        )
    }

    single<GetUserDataFromSecureStorageUseCase> {
        GetUserDataFromSecureStorageUseCaseImpl(secureStorage = get())
    }

    single<GetAuthTokenFromEncStoreUseCase> {
        GetAuthTokenFromEncStoreUseCaseImpl(secureStorage = get())
    }

    single<GetUserDataUseCase> {
        GetUserDataUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<SaveUserNameUseCase> {
        SaveUserNameUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<SaveUserRoleUseCase> {
        SaveUserRoleUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<SignOutUseCase> {
        SignOutUseCaseImpl(userContext = get())
    }

    single<DeleteUserAccountUseCase> {
        DeleteUserAccountUseCaseImpl(
            authRepository = get(),
            userContext = get()
        )
    }

    single<DeleteOwnAccountUseCase> {
        DeleteOwnAccountUseCaseImpl(
            authRepository = get(),
            userContext = get(),
            signOutUseCase = get()
        )
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

    viewModel { parameters ->
        ProfileViewModel(
            userContext = get(),
            userId = parameters.getOrNull<Int>()?.takeIf { it != 0 },
            getUserDataUseCase = get(),
            saveUserNameUseCase = get(),
            saveUserNameToSecureStorageUseCase = get(),
            saveUserRoleUseCase = get()
        )
    }

    viewModel {
        SignOutViewModel(signOutUseCase = get())
    }

    viewModel {
        DeleteOwnAccountViewModel(deleteOwnAccountUseCase = get())
    }

    viewModel { parameters ->
        DeleteUserAccountViewModel(
            userId = parameters.get<Int>(),
            deleteUserAccountUseCase = get()
        )
    }

    /* ---------- Other ---------- */

    single {
        UserContext(
            checkAuthTokenValidityUseCase = get(),
            saveUserDataToSecureStorageUseCase = get(),
            getUserDataFromSecureStorageUseCase = get(),
            getAuthTokenFromEncStoreUseCase = get()
        )
    }

}