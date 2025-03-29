package cz.cvut.docta.leaderboard.domain.usecase

import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem

class GetLeaderboardUseCaseImpl : GetLeaderboardUseCase {
    override suspend fun execute(): List<LeaderboardItem> {

        // TODO-LEADERBOARD: get data from data layer

        return emptyList()
    }
}