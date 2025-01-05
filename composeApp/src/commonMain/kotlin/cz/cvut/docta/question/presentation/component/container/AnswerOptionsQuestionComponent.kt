package cz.cvut.docta.question.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput

@Composable
fun AnswerOptionsQuestionComponent(
    questionWithAnswerInput: QuestionWithAnswerInput.AnswerOptions,
    onOptionChoose: (Long) -> Unit,
    onCheckButtonClick: () -> Unit
) {
    QuestionComponentContainerWithCheckButton(
        question = questionWithAnswerInput.question,
        checkIsAllowed = questionWithAnswerInput.checkIsAllowed,
        onCheckButtonClick = onCheckButtonClick
    ) {
        // TODO-QUESTION
    }
}