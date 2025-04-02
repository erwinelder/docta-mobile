package cz.cvut.docta.lessonSession.data.model.question

import cz.cvut.docta.lessonSession.data.model.answer.AnswerTextDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("classType")
sealed class QuestionDto {
    abstract val courseCode: String
    abstract val id: Long
    abstract val difficulty: QuestionDifficultyDto

    @Serializable
    @SerialName("OpenAnswer")
    data class OpenAnswer(
        override val courseCode: String,
        override val id: Long,
        override val difficulty: QuestionDifficultyDto,
        val text: String
    ) : QuestionDto()

    @Serializable
    @SerialName("FillInBlanks")
    data class FillInBlanks(
        override val courseCode: String,
        override val id: Long,
        override val difficulty: QuestionDifficultyDto,
        val text: String
    ) : QuestionDto()

    @Serializable
    @SerialName("SingleOption")
    data class SingleOption(
        override val courseCode: String,
        override val id: Long,
        override val difficulty: QuestionDifficultyDto,
        val text: String,
        val options: List<AnswerTextDto>
    ) : QuestionDto()

    @Serializable
    @SerialName("Ordering")
    data class Ordering(
        override val courseCode: String,
        override val id: Long,
        override val difficulty: QuestionDifficultyDto,
        val text: String,
        val orderOptions: List<AnswerTextDto>
    ) : QuestionDto()

    @Serializable
    @SerialName("QuestionAnswerPairs")
    data class QuestionAnswerPairs(
        override val courseCode: String,
        override val id: Long,
        override val difficulty: QuestionDifficultyDto,
        val text: String,
        val questionPairs: List<AnswerTextDto>,
        val answerPairs: List<AnswerTextDto>
    ) : QuestionDto()

}