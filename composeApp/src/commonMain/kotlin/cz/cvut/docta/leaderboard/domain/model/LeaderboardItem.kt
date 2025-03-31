package cz.cvut.docta.leaderboard.domain.model

data class LeaderboardItem(
    val name: String,
    val points: Double,
    val isCurrentUser: Boolean
)
