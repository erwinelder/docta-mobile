package cz.cvut.docta.achievement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.achievement.domain.model.Achievement
import cz.cvut.docta.achievement.domain.usecase.GetAchievementsUseCase
import cz.cvut.docta.achievement.presentation.model.AchievementUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AchievementsViewModel(
    private val getAchievementsUseCase: GetAchievementsUseCase
) : ViewModel() {

    private val _achievements = MutableStateFlow<List<AchievementUiState>>(emptyList())
    val achievements = _achievements.asStateFlow()

    private fun applyAchievements(achievements: List<Achievement>) {
        _achievements.update {
            achievements.map {
                AchievementUiState.fromAchievement(achievement = it)
            }
        }
    }

    private fun fetchAchievements() {
        viewModelScope.launch {
            applyAchievements(achievements = getAchievementsUseCase.execute())
        }
    }

    init {
        fetchAchievements()
    }

}