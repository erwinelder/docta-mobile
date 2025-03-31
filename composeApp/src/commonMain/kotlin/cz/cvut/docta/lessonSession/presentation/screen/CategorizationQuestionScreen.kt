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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.core.presentation.component.container.GlassSurface
import cz.cvut.docta.core.presentation.component.picker.PickerButton
import cz.cvut.docta.core.presentation.component.picker.PopupPicker
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.core.presentation.theme.Manrope
import cz.cvut.docta.lessonSession.domain.model.question.QuestionCheckResult
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.question.CategorizationOptionUiState
import cz.cvut.docta.lessonSession.presentation.model.question.CategoryUiState
import cz.cvut.docta.lessonSession.presentation.model.question.Materials
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CategorizationQuestionScreen(
    screenPadding: PaddingValues,
    questionText: String,
    questionMaterials: List<Materials>,
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
        questionMaterials = questionMaterials,
        buttonIsEnabled = checkIsAllowed,
        showCheckButton = checkResult == null,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) {
        AnimatedContent(
            targetState = checkResult
        ) { result ->
            if (result == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = questionText,
                        fontSize = 18.sp,
                        color = DoctaColors.onSurface,
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 0.dp),
            horizontalArrangement = Arrangement.End
        ) {
            CategoryPicker(
                selectedCategoryName = option.selectedCategoryName,
                selectedCategoryId = option.selectedCategoryId,
                categories = categories,
                onSelect = { categoryId ->
                    onCategorySelect(option.id, categoryId)
                }
            )
        }

        GlassSurface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            cornerSize = 12.dp,
            borderSize = 1.dp
        ) {
            Text(
                text = option.text,
                color = DoctaColors.onSurface,
                fontSize = 16.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
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
    val selected = selectedCategoryName ?: "Category text"

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


