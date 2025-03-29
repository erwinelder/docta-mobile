package cz.cvut.docta.leaderboard.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.leaderboard.domain.model.LeaderboardItem
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.star_filled_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun LeaderboardScreen(
    leaders: List<LeaderboardItem>,
    screenPadding: PaddingValues = PaddingValues()
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        screenPadding = screenPadding
    ) {
        Text(
            text = "This section is still under development",
            textAlign = TextAlign.Center,
            fontFamily = Manrope,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
//        Text(
//            text = stringResource(SharedRes.strings.leaderboard),
//            style = DoctaTypography.titleMedium,
//            modifier = Modifier.fillMaxWidth()
//        )
//        ListPicker(
//            items = listOf(),
//            selectedItem = "Architecture",
//            onItemSelect = {},
//        )
//        LeadersList(leaders)
    }
}

@Composable
fun ColumnScope.LeadersList(
    leaders: List<LeaderboardItem>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.weight(1f),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items = leaders) { leader ->
            LeaderListItem(
                leader = leader
            )
        }
    }
}

@Composable
fun LeaderListItem(
    leader: LeaderboardItem
) {
    val transparentGradient = listOf(
        Color.Transparent, Color.Transparent
    )

    val itemTextStyle = TextStyle(
        fontSize = 16.sp
    )

    GlassSurface(
        gradientColor = if (leader.isCurrentUser) DoctaColors.glassSurfaceGradient else transparentGradient,
        borderSize = 0.dp,
        cornerSize = 10.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text(
                text = leader.name,
                style = itemTextStyle
            )
            LeaderListItemPoints(
                points = leader.points,
                textStyle = itemTextStyle
            )
        }
    }
}

@Composable
fun LeaderListItemPoints(
    points: Double,
    textStyle: TextStyle
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = points.toString(),
            style = textStyle,
            textAlign = TextAlign.Center
        )
        Icon(
            painter = painterResource(Res.drawable.star_filled_icon),
            contentDescription = "Star icon",
            tint = DoctaColors.yellow,
            modifier = Modifier.size(20.dp)
        )
    }
}