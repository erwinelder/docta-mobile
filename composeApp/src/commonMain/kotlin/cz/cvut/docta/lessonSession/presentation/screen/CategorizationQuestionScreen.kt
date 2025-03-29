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
import cz.cvut.docta.core.presentation.component.picker.PickerButton
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
        questionInstructions = stringResource(SharedRes.strings.answer_option_question_instructions),
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
                        CategorizationOptionRow(
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
fun CategorizationOptionRow(
    option: CategorizationOptionUiState,
    categories: List<CategoryUiState>,
    onCategorySelect: (Long, Long) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    val selectedCategoryName = categories
        .find { it.id == option.selectedCategoryId }
        ?.name ?: "Category text"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PickerButton(
                text = selectedCategoryName,
                isExpanded = isExpanded,
                selectedColor = DoctaColors.onSurface,
                onClick = { isExpanded = !isExpanded }
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onCategorySelect(option.id, category.id)
                    }
                ) {
                    Text(text = category.name)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp)
                .padding(top = 4.dp)
                .border(1.dp, DoctaColors.glassSurfaceBorder, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = DoctaColors.glassSurfaceGradient,
                        start = Offset(75f, 210f),
                        end = Offset(95f, -10f)
                    )
                )
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Text(
                text = option.text,
                color = DoctaColors.onSurface,
                fontSize = 16.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal
            )
        }
    }
}