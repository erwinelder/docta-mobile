package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lessonSession.data.model.CompletedLessonStatsDto
import cz.cvut.docta.lessonSession.data.model.MaterialsDto
import cz.cvut.docta.lessonSession.data.model.QuestionWrapperDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerCheckResultDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerInputDto
import cz.cvut.docta.lessonSession.data.model.answer.AnswerTextDto
import cz.cvut.docta.lessonSession.data.model.question.QuestionDifficultyDto
import cz.cvut.docta.lessonSession.data.model.question.QuestionDto
import cz.cvut.docta.lessonSession.domain.model.CompletedLessonStats
import cz.cvut.docta.lessonSession.domain.model.Materials
import cz.cvut.docta.lessonSession.domain.model.question.QuestionWithMaterials
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerCheckResult
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerInput
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.domain.model.question.QuestionDifficulty


fun LessonWithProgress.getSessionOptions(): SessionOptions {
    return when (this) {
        is LessonWithProgress.Default -> SessionOptions.Default(lessonId = this.id)
        is LessonWithProgress.Test -> SessionOptions.Default(lessonId = this.id)
        is LessonWithProgress.StepByStep -> SessionOptions.StepByStep(lessonId = this.id)
    }
}


fun SessionOptions.toDto(courseCode: String): SessionOptionsDto {
    return when (this) {
        is SessionOptions.Default -> SessionOptionsDto.Default(
            courseCode = courseCode,
            lessonId = lessonId
        )
        is SessionOptions.StepByStep -> SessionOptionsDto.StepByStep(
            courseCode = courseCode,
            lessonId = lessonId
        )
    }
}


fun QuestionDifficultyDto.toDomainModel(): QuestionDifficulty {
    return when (this) {
        QuestionDifficultyDto.Simple -> QuestionDifficulty.Simple
        QuestionDifficultyDto.Hard -> QuestionDifficulty.Hard
    }
}

fun QuestionDifficulty.toDto(): QuestionDifficultyDto {
    return when (this) {
        QuestionDifficulty.Simple -> QuestionDifficultyDto.Simple
        QuestionDifficulty.Hard -> QuestionDifficultyDto.Hard
    }
}


fun QuestionWrapperDto.toDomainModel(): QuestionWithMaterials {
    return when (this) {
        is QuestionWrapperDto.OpenAnswer -> QuestionWithMaterials.OpenAnswer(
            question = this.question.toDomainModel(),
            materials = this.materials.map { it.toDomainModel() }
        )
        is QuestionWrapperDto.FillInBlanks -> QuestionWithMaterials.FillInBlanks(
            question = this.question.toDomainModel(),
            materials = this.materials.map { it.toDomainModel() }
        )
        is QuestionWrapperDto.SingleOption -> QuestionWithMaterials.SingleOption(
            question = this.question.toDomainModel(),
            materials = this.materials.map { it.toDomainModel() }
        )
        is QuestionWrapperDto.Ordering -> QuestionWithMaterials.Ordering(
            question = this.question.toDomainModel(),
            materials = this.materials.map { it.toDomainModel() }
        )
        is QuestionWrapperDto.QuestionAnswerPairs -> QuestionWithMaterials.QuestionAnswerPairs(
            question = this.question.toDomainModel(),
            materials = this.materials.map { it.toDomainModel() }
        )
    }
}

fun QuestionDto.OpenAnswer.toDomainModel(): Question.OpenAnswer {
    return Question.OpenAnswer(id = id, difficulty = difficulty.toDomainModel(), text = text)
}

fun QuestionDto.FillInBlanks.toDomainModel(): Question.FillInBlanks {
    return Question.FillInBlanks(id = id, difficulty = difficulty.toDomainModel(), text = text)
}

fun QuestionDto.SingleOption.toDomainModel(): Question.SingleOption {
    return Question.SingleOption(
        id = id,
        difficulty = difficulty.toDomainModel(),
        text = text,
        options = options.map { it.toDomainModel() }
    )
}

fun QuestionDto.Ordering.toDomainModel(): Question.Ordering {
    return Question.Ordering(
        id = id,
        difficulty = difficulty.toDomainModel(),
        text = text,
        orderOptions = orderOptions.map { it.toDomainModel() }
    )
}

fun QuestionDto.QuestionAnswerPairs.toDomainModel(): Question.QuestionAnswerPairs {
    return Question.QuestionAnswerPairs(
        id = id,
        difficulty = difficulty.toDomainModel(),
        text = text,
        questionPairs = questionPairs.map { it.toDomainModel() },
        answerPairs = answerPairs.map { it.toDomainModel() }
    )
}


fun AnswerTextDto.toDomainModel(): AnswerText {
    return AnswerText(
        id = id,
        text = text
    )
}


fun MaterialsDto.toDomainModel(): Materials {
    return Materials(
        id = id,
        text = text
    )
}


fun AnswerInput.toDto(): AnswerInputDto {
    return when (this) {
        is AnswerInput.Open -> AnswerInputDto.Open(
            questionId = questionId,
            answer = answer
        )
        is AnswerInput.Blanks -> AnswerInputDto.Blanks(
            questionId = questionId,
            answers = answers
        )
        is AnswerInput.SingleOption -> AnswerInputDto.SingleOption(
            questionId = questionId,
            id = id
        )
        is AnswerInput.CategorizedOptions -> AnswerInputDto.CategorizedOptions(
            questionId = questionId,
            optionsCategoryToMap = optionToCategoryMap
        )
        is AnswerInput.QuestionAnswerPairs -> AnswerInputDto.QuestionAnswerPairs(
            questionId = questionId,
            hadErrors = hadErrors
        )
    }
}


fun AnswerCheckResultDto.toDomainModel(): AnswerCheckResult {
    return when (this) {
        is AnswerCheckResultDto.Open -> AnswerCheckResult.Open(
            isCorrect = isCorrect,
            answer = answer
        )
        is AnswerCheckResultDto.Blanks -> AnswerCheckResult.Blanks(
            isCorrect = isCorrect,
            answers = answers
        )
        is AnswerCheckResultDto.SingleOption -> AnswerCheckResult.SingleOption(
            isCorrect = isCorrect,
            id = id
        )
        is AnswerCheckResultDto.CategorizedOptions -> AnswerCheckResult.CategorizedOptions(
            isCorrect = isCorrect,
            optionToCategory = optionToCategory
        )
        is AnswerCheckResultDto.QuestionAnswerPairs -> AnswerCheckResult.QuestionAnswerPairs(
            isCorrect = isCorrect
        )
    }
}


fun CompletedLessonStatsDto.toDomainModel(): CompletedLessonStats {
    return CompletedLessonStats(
        percentage = percentage,
        points = points,
        mistakeCount = mistakeCount
    )
}
