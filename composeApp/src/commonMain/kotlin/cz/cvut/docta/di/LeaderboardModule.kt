package cz.cvut.docta.di

import cz.cvut.docta.leaderboard.domain.usecase.GetLeaderboardUseCase
import cz.cvut.docta.leaderboard.domain.usecase.GetLeaderboardUseCaseImpl
import cz.cvut.docta.leaderboard.presentation.viewmodel.LeaderboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val leaderboardModule = module {

    /* ---------- Use Cases ---------- */

    single<GetLeaderboardUseCase> {
        GetLeaderboardUseCaseImpl()
    }

    /* ---------- View Models ---------- */

    viewModel {
        LeaderboardViewModel(getLeaderboardUseCase = get())
    }

}