package cz.cvut.docta.leaderboard.domain.model

/**
 * Leaderboard item
 */
data class LeaderboardItem(
    val name: String,
    val points: Double,
    val isCurrentUser: Boolean
)
