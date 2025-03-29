package cz.cvut.docta.leaderboard.domain.usecase

import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem

interface GetLeaderboardUseCase {
    suspend fun execute(): List<LeaderboardItem>
}