package cz.cvut.docta.lessonSession.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class QuestionWithCorrectAnswersDto {

    @Serializable
    @SerialName("OpenAnswer")
    data class OpenAnswer(
        val question: QuestionDto.OpenAnswer,
        val answers: CorrectAnswerDto.OpenAnswers
    ) : QuestionWithCorrectAnswersDto()

    @Serializable
    @SerialName("FillInBlanks")
    data class FillInBlanks(
        val question: QuestionDto.FillInBlanks,
        val blanksAnswers: CorrectAnswerDto.Blanks
    ) : QuestionWithCorrectAnswersDto()

    @Serializable
    @SerialName("AnswerOptions")
    data class AnswerOptions(
        val question: QuestionDto.AnswerOptions,
        val answer: CorrectAnswerDto.Option
    ) : QuestionWithCorrectAnswersDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        val question: QuestionDto.QuestionAnswerPairs
    ) : QuestionWithCorrectAnswersDto()

    @Serializable
    @SerialName("StepByStep")
    data class StepByStep(
        val question: QuestionDto.StepByStep,
        val answer: CorrectAnswerDto.StepAnswer
    ) : QuestionWithCorrectAnswersDto()

}