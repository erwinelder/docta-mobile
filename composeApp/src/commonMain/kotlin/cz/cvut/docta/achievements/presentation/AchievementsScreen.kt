package cz.cvut.docta.achievements.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.achievements.domain.model.Achievement
import cz.cvut.docta.achievements.presentation.component.CircleProgressBar
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.DoctaTypography
import dev.icerock.moko.resources.compose.stringResource
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.achievement_placheholder_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun AchievementsScreen(
    achievements: List<Achievement>
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = stringResource(SharedRes.strings.achievements_screen_title),
            style = DoctaTypography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
        )
        AchievementsList(achievements)
    }
}

@Composable
fun AchievementsList(
    achievements: List<Achievement>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(items = achievements) { achievement ->
            AchievementListItem(
                achievement = achievement
            )
        }
    }
}

@Composable
fun AchievementListItem(
    achievement: Achievement,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        // Wrapper for the achievement icon
        Box(modifier = Modifier.size(64.dp)) {
            Icon(
                // TODO: Get icon from the code
                painter = painterResource(Res.drawable.achievement_placheholder_icon),
                contentDescription = achievement.title,
                modifier = Modifier.fillMaxSize()
            )
            CircleProgressBar(
                percentage = achievement.percentage.toFloat(),
                modifier = Modifier.fillMaxSize().padding(5.dp),
                strokeSize = 5.dp,
                color = DoctaColors.yellow
            )
        }
        // Achievement title
        Text(
            text = achievement.title,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}