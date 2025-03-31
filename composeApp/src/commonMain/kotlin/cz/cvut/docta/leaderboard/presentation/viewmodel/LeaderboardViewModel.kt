package cz.cvut.docta.leaderboard.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem
import cz.cvut.docta.leaderboard.domain.usecase.GetLeaderboardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val getLeaderboardUseCase: GetLeaderboardUseCase
) : ViewModel() {

    private val _leaders = MutableStateFlow<List<LeaderboardItem>>(emptyList())
    val leaders = _leaders.asStateFlow()

    private fun applyLeaders(leaders: List<LeaderboardItem>) {
        _leaders.update {
            leaders
        }
    }

    private fun fetchLeaders() {
        viewModelScope.launch {
            applyLeaders(leaders = getLeaderboardUseCase.execute())
        }
    }

    init {
        fetchLeaders()
    }

}