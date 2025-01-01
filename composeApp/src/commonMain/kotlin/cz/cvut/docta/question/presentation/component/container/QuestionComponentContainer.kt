package cz.cvut.docta.question.presentation.component.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import cz.cvut.docta.question.domain.model.Question
import cz.cvut.docta.question.presentation.component.text.QuestionInstructionsTitle

@Composable
fun QuestionComponentContainer(
    question: Question,
    content: @Composable () -> Unit,
    checkButton: (@Composable () -> Unit)?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        QuestionInstructionsTitle(question = question)
        content()
        checkButton?.invoke()
    }
}