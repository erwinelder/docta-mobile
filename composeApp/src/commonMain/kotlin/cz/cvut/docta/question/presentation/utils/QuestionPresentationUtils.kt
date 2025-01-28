package cz.cvut.docta.question.presentation.utils

import cz.cvut.docta.question.presentation.model.QuestionAndAnswersWrapper
import docta.composeapp.generated.resources.Res
import docta.composeapp.generated.resources.answer_option_question_instructions
import docta.composeapp.generated.resources.fill_in_blanks_question_instructions
import docta.composeapp.generated.resources.open_answer_question_instructions
import docta.composeapp.generated.resources.question_answer_pairs_question_instructions
import docta.composeapp.generated.resources.step_question_instructions
import org.jetbrains.compose.resources.StringResource


fun QuestionAndAnswersWrapper.getQuestionInstructions(): StringResource {
    return when (this) {
        is QuestionAndAnswersWrapper.OpenAnswer -> Res.string.open_answer_question_instructions
        is QuestionAndAnswersWrapper.FillInBlanks -> Res.string.fill_in_blanks_question_instructions
        is QuestionAndAnswersWrapper.AnswerOptions -> Res.string.answer_option_question_instructions
        is QuestionAndAnswersWrapper.QuestionAnswerPairs -> Res.string.question_answer_pairs_question_instructions
        is QuestionAndAnswersWrapper.StepByStep -> Res.string.step_question_instructions
    }
}