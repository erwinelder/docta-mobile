package cz.cvut.docta.question.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.core.presentation.component.buttons.PrimaryButton
import cz.cvut.docta.question.domain.model.Question

@Composable
fun QuestionComponentContainerWithCheckButton(
    question: Question,
    checkIsAllowed: Boolean,
    onCheckButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    QuestionComponentContainer(
        question = question,
        content = content,
        checkButton = {
            PrimaryButton(
                // TODO-STRING-RESOURCE
                text = "Check",
                enabled = checkIsAllowed,
                onClick = onCheckButtonClick
            )
        }
    )
}