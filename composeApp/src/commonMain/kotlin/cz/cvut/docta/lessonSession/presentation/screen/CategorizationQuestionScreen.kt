import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.picker.PickerButton
import cz.cvut.docta.core.presentation.component.picker.PopupPicker
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.question.CategorizationOptionUiState
import cz.cvut.docta.lessonSession.presentation.model.question.CategoryUiState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CategorizationQuestionScreen(
    screenPadding: PaddingValues,
    questionText: String,
    options: List<CategorizationOptionUiState>,
    categories: List<CategoryUiState>,
    onCategorySelect: (Long, Long) -> Unit,
    checkIsAllowed: Boolean,
    checkResult: QuestionCheckResult?,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.categorization_question_instructions),
        buttonIsEnabled = checkIsAllowed,
        showCheckButton = checkResult == null,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) {
        Text(
            text = questionText,
            fontSize = 18.sp,
            color = DoctaColors.onSurface,
            fontFamily = Manrope,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        )

        AnimatedContent(
            targetState = checkResult
        ) { result ->
            if (result == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { optionUiState ->
                        CategoryAndOptionBlock(
                            option = optionUiState,
                            categories = categories,
                            onCategorySelect = onCategorySelect
                        )
                    }
                }
            } else {
                Text(
                    text = if (result.isCorrect) "Correct" else "Incorrect",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun CategoryAndOptionBlock(
    option: CategorizationOptionUiState,
    categories: List<CategoryUiState>,
    onCategorySelect: (Long, Long) -> Unit
) {
    val surfaceWidth = FilledWidthByScreenType()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(surfaceWidth.getByType(CurrWindowType))
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(modifier = Modifier.widthIn(max = 180.dp)) {
                    CategoryPicker(
                        selectedCategoryId = option.selectedCategoryId,
                        selectedCategoryName = option.selectedCategoryName,
                        categories = categories,
                        onSelect = { categoryId ->
                            onCategorySelect(option.id, categoryId)
                        }
                    )
                }
            }
        }

        GlassSurface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            cornerSize = 12.dp,
            borderSize = 1.dp
        ) {
            Text(
                text = option.text,
                color = DoctaColors.onSurface,
                fontSize = 16.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )
        }
    }
}


@Composable
private fun CategoryPicker(
    selectedCategoryId: Long?,
    selectedCategoryName: String?,
    categories: List<CategoryUiState>,
    onSelect: (Long) -> Unit
) {
    val selected = selectedCategoryName ?: stringResource(SharedRes.strings.select_category)

    val selectedCategory = CategoryUiState(
        id = selectedCategoryId ?: -1L,
        name = selected
    )

    PopupPicker(
        selectedItem = selectedCategory,
        itemList = categories,
        itemToStringMapper = { it.name },
        onItemSelect = { onSelect(it.id) },
        outerPadding = PaddingValues(0.dp)
    )
}


