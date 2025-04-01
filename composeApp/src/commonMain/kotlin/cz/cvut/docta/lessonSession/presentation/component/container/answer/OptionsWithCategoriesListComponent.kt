package cz.cvut.docta.lessonSession.presentation.component.container.answer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.picker.PopupPicker
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import cz.cvut.docta.lessonSession.presentation.model.answer.OptionWithCategory
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OptionsWithCategoriesListComponent(
    optionsWithCategories: List<OptionWithCategory>,
    categories: List<AnswerText>,
    onOptionCategorySelect: (Long, Long) -> Unit,
    checkState: AnswerCheckState<AnswerCheckResult.CategorizedOptions>
) {
    val isIdle = checkState is AnswerCheckState.Idle
    val result = checkState.getResultOrNull()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = optionsWithCategories) { optionWithCategory ->
            OptionWithCategoryComponent(
                optionWithCategory = optionWithCategory,
                categories = categories,
                onOptionCategorySelect = onOptionCategorySelect,
                isIdle = isIdle,
                result = result
            )
        }
    }
}

@Composable
private fun OptionWithCategoryComponent(
    optionWithCategory: OptionWithCategory,
    categories: List<AnswerText>,
    onOptionCategorySelect: (Long, Long) -> Unit,
    isIdle: Boolean,
    result: AnswerCheckResult.CategorizedOptions?
) {
    val category = optionWithCategory.category ?: AnswerText(
        id = 0, stringResource(SharedRes.strings.select_category)
    )
    val color = when {
        result != null -> if (
            result.optionToCategory[optionWithCategory.optionId] == optionWithCategory.category?.id
        ) {
            DoctaColors.successGlassGradientPair
        } else {
            DoctaColors.errorGlassGradientPair
        }
        else -> DoctaColors.glassSurfaceGradientPair
    }

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PopupPicker(
            selectedItem = category,
            itemList = categories,
            itemToStringMapper = { it.text },
            onItemSelect = { category ->
                onOptionCategorySelect(optionWithCategory.optionId, category.id)
            },
            fontSize = 18.sp,
            buttonGradientColor = color,
            enabled = isIdle,
        )
        GlassSurface(cornerSize = 16.dp) {
            Text(
                text = optionWithCategory.optionText,
                color = DoctaColors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                fontFamily = Manrope,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    }
}