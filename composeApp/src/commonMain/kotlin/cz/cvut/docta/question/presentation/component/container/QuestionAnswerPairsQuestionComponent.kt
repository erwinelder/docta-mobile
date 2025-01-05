package cz.cvut.docta.question.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput

@Composable
fun QuestionAnswerPairsQuestionComponent(
    questionWithAnswerInput: QuestionWithAnswerInput.QuestionAnswerPairs,
    onPairQuestionSelect: (Long) -> Unit,
    onPairAnswerSelect: (Long) -> Unit
) {
    QuestionComponentContainerWithoutCheckButton(
        question = questionWithAnswerInput.question
    ) {
        // TODO-QUESTION
    }
}