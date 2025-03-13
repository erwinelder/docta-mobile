package cz.cvut.docta.di

import cz.cvut.docta.achievement.data.repository.AchievementRemoteRepository
import cz.cvut.docta.achievement.data.repository.AchievementRepository
import cz.cvut.docta.achievement.domain.usecase.GetAchievementsUseCase
import cz.cvut.docta.achievement.domain.usecase.GetAchievementsUseCaseImpl
import cz.cvut.docta.achievement.presentation.viewmodel.AchievementsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val achievementModule = module {

    /* ---------- Repositories ---------- */

    single<AchievementRepository> {
        AchievementRemoteRepository()
    }

    /* ---------- Use Cases ---------- */

    single<GetAchievementsUseCase> {
        GetAchievementsUseCaseImpl(achievementRepository = get())
    }

    /* ---------- View Models ---------- */

    viewModel {
        AchievementsViewModel(
            getAchievementsUseCase = get()
        )
    }

}