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
import cz.cvut.docta.lessonSession.presentation.component.container.question.QuestionText
import cz.cvut.docta.lessonSession.presentation.component.field.answer.OpenAnswerTextField
import cz.cvut.docta.lessonSession.presentation.component.screenContainer.QuestionScreenContainer
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckRequestState
import cz.cvut.docta.lessonSession.presentation.model.answer.AnswerCheckState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun OpenAnswerQuestionScreen(
    screenPadding: PaddingValues,
    questionMaterials: List<Materials>,
    questionText: String,
    answerText: String,
    onAnswerChange: (String) -> Unit,
    checkIsAllowed: Boolean,
    checkRequestState: AnswerCheckRequestState<AnswerCheckResult.Open>,
    onCheckButtonClick: () -> Unit,
    onContinueButtonClick: () -> Unit
) {
    QuestionScreenContainer(
        screenPadding = screenPadding,
        questionInstructions = stringResource(SharedRes.strings.open_answer_question_instructions),
        questionMaterials = questionMaterials,
        buttonIsEnabled = checkIsAllowed,
        checkRequestState = checkRequestState,
        onCheckButtonClick = onCheckButtonClick,
        onContinueButtonClick = onContinueButtonClick
    ) { checkState ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuestionText(text = questionText)
            OpenAnswerTextField(
                text = answerText,
                onValueChange = onAnswerChange,
                checkResult = checkState.getResultOrNull(),
                readOnly = checkState !is AnswerCheckState.Idle
            )
        }
    }
}