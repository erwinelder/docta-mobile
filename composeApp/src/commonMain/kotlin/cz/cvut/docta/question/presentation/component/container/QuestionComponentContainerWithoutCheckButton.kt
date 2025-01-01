package cz.cvut.docta.question.presentation.component.container

import androidx.compose.runtime.Composable
import cz.cvut.docta.question.domain.model.Question

@Composable
fun QuestionComponentContainerWithoutCheckButton(
    question: Question,
    content: @Composable () -> Unit
) {
    QuestionComponentContainer(
        question = question,
        content = content,
        checkButton = null
    )
}