package cz.cvut.docta.lessonSession.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.SharedRes
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.presentation.component.container.answer.OptionsWithCategoriesListComponent
import cz.cvut.docta.lessonSession.presentation.component.container.question.QuestionText
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.OptionWithCategory
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun CategorizationQuestionScreen(
    screenPadding: PaddingValues,
    questionMaterials: List<Materials>,
    questionText: String,
    categories: List<AnswerText>,
    optionsWithCategories: List<OptionWithCategory>,
    onOptionCategorySelect: (Long, Long) -> Unit,
    checkIsAllowed: Boolean,
    checkRequestState: AnswerCheckRequestState<AnswerCheckResult.CategorizedOptions>,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.categorization_question_instructions),
        questionMaterials = questionMaterials,
        buttonIsEnabled = checkIsAllowed,
        checkRequestState = checkRequestState,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) { checkState ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuestionText(text = questionText)
            OptionsWithCategoriesListComponent(
                optionsWithCategories = optionsWithCategories,
                categories = categories,
                onOptionCategorySelect = onOptionCategorySelect,
                checkState = checkState
            )
        }
    }
}