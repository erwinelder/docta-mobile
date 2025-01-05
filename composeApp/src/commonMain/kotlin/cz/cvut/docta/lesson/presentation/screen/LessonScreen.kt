package cz.cvut.docta.lesson.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.docta.answer.presentation.model.AnswerInput
import cz.cvut.docta.core.domain.app.FilledWidthByScreenType
import cz.cvut.docta.core.presentation.component.other.ProgressBar
import cz.cvut.docta.core.presentation.component.screenContainers.ScreenContainer
import cz.cvut.docta.core.presentation.theme.CurrWindowType
import cz.cvut.docta.core.presentation.theme.DoctaColors
import cz.cvut.docta.question.domain.model.Question
import cz.cvut.docta.question.domain.model.QuestionCheckResult
import cz.cvut.docta.question.domain.model.QuestionDifficulty
import cz.cvut.docta.question.domain.model.QuestionWithAnswerInput
import cz.cvut.docta.question.presentation.component.container.AnswerOptionsQuestionComponent
import cz.cvut.docta.question.presentation.component.container.FillInBlanksQuestionComponent
import cz.cvut.docta.question.presentation.component.container.OpenAnswerQuestionComponent
import cz.cvut.docta.question.presentation.component.container.QuestionAnswerPairsQuestionComponent
import cz.cvut.docta.question.presentation.component.container.StepByStepQuestionComponent

@Composable
fun LessonScreen(
    progression: IntProgression = IntProgression.fromClosedRange(
        rangeStart = 1,
        rangeEnd = 15,
        step = 4
    ),
    questionWithAnswerInput: QuestionWithAnswerInput? = QuestionWithAnswerInput.OpenAnswer(
        question = Question.OpenAnswer(
            id = 1,
            difficulty = QuestionDifficulty.Medium,
            text = "What is the ___ result of the ___ integral of x^2?"
        ),
        answerInput = AnswerInput.Open(answer = ""),
        checkIsAllowed = false
    ),
    questionCheckResult: QuestionCheckResult? = null,
    onContinueButtonClick: () -> Unit = {},

    onOpenAnswerInputChange: (String) -> Unit = {},
    onCheckOpenAnswer: () -> Unit = {},

    onBlanksAnswerInputChange: (Int, String) -> Unit = { _, _ -> },
    onCheckBlanksAnswers: () -> Unit = {},

    onAnswerOptionSelect: (Long) -> Unit = { _ -> },
    onCheckOptionAnswer: () -> Unit = {},

    onPairQuestionSelect: (Long) -> Unit = { _ -> },
    onPairAnswerSelect: (Long) -> Unit = { _ -> },

    onStepAnswerInputChange: (String) -> Unit = { _ -> },
    onCheckStepAnswer: () -> Unit = {}
) {
    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(FilledWidthByScreenType().getByType(CurrWindowType))
        ) {
            ProgressBar(progression = progression, colorPair = DoctaColors.primaryGradientPair)
        }
        AnimatedContent(
            targetState = questionWithAnswerInput,
            modifier = Modifier.weight(1f)
        ) { targetQuestionWithAnswerInput ->
            when (targetQuestionWithAnswerInput) {
                is QuestionWithAnswerInput.OpenAnswer -> OpenAnswerQuestionComponent(
                    questionWithAnswerInput = targetQuestionWithAnswerInput,
                    onAnswerChange = onOpenAnswerInputChange,
                    onCheckButtonClick = onCheckOpenAnswer
                )
                is QuestionWithAnswerInput.FillInBlanks -> FillInBlanksQuestionComponent(
                    questionWithAnswerInput = targetQuestionWithAnswerInput,
                    onValueChange = onBlanksAnswerInputChange,
                    onCheckButtonClick = onCheckBlanksAnswers
                )
                is QuestionWithAnswerInput.AnswerOptions -> AnswerOptionsQuestionComponent(
                    questionWithAnswerInput = targetQuestionWithAnswerInput,
                    onOptionChoose = onAnswerOptionSelect,
                    onCheckButtonClick = onCheckOptionAnswer
                )
                is QuestionWithAnswerInput.QuestionAnswerPairs -> QuestionAnswerPairsQuestionComponent(
                    questionWithAnswerInput = targetQuestionWithAnswerInput,
                    onPairQuestionSelect = onPairQuestionSelect,
                    onPairAnswerSelect = onPairAnswerSelect
                )
                is QuestionWithAnswerInput.Step -> StepByStepQuestionComponent(
                    questionWithAnswerInput = targetQuestionWithAnswerInput,
                    onStepAnswerInputChange = onStepAnswerInputChange,
                    onCheckButtonClick = onCheckStepAnswer
                )
                null -> {}
            }
        }
    }
}