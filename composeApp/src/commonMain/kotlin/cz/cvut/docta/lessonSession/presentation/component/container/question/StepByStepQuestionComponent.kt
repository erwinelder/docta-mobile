package cz.cvut.docta.lessonSession.presentation.component.container.question

import androidx.compose.runtime.Composable
import cz.cvut.docta.lessonSession.domain.model.QuestionWithAnswerInput

@Composable
fun StepByStepQuestionComponent(
    questionWithAnswerInput: QuestionWithAnswerInput.Step,
    onStepAnswerInputChange: (String) -> Unit,
    onCheckButtonClick: () -> Unit
) {
    /*QuestionComponentContainerWithCheckButton(
        question = questionWithAnswerInput.question,
        checkIsAllowed = questionWithAnswerInput.checkIsAllowed,
        onCheckButtonClick = onCheckButtonClick
    ) {
        // TODO-QUESTION
    }*/
}