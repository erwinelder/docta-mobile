package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lessonSession.data.model.AnswerTextDto
import cz.cvut.docta.lessonSession.data.model.CorrectAnswerDto
import cz.cvut.docta.lessonSession.data.model.QuestionDto
import cz.cvut.docta.lessonSession.data.model.QuestionWithCorrectAnswersDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lessonSession.domain.model.question.Question

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

fun QuestionWithCorrectAnswersDto.toDomainModel(): QuestionWithCorrectAnswers {
    return when (this) {
        is QuestionWithCorrectAnswersDto.OpenAnswer -> QuestionWithCorrectAnswers.OpenAnswer(
            question = this.question.toDomainModel(),
            answers = this.answers.toDomainModel()
        )
        is QuestionWithCorrectAnswersDto.FillInBlanks -> QuestionWithCorrectAnswers.FillInBlanks(
            question = this.question.toDomainModel(),
            blanksAnswers = this.blanksAnswers.toDomainModel()
        )
        is QuestionWithCorrectAnswersDto.AnswerOptions -> QuestionWithCorrectAnswers.AnswerOptions(
            question = this.question.toDomainModel(),
            answers = this.answers.map { it.toDomainModel() }
        )
        is QuestionWithCorrectAnswersDto.QuestionAnswerPairs -> QuestionWithCorrectAnswers.QuestionAnswerPairs(
            question = this.question.toDomainModel()
        )
        is QuestionWithCorrectAnswersDto.StepByStep -> QuestionWithCorrectAnswers.StepByStep(
            question = this.question.toDomainModel(),
            answer = this.answer.toDomainModel()
        )
    }
}

fun QuestionDto.toDomainModel(): Question {
    return when (this) {
        is QuestionDto.OpenAnswer -> this.toDomainModel()
        is QuestionDto.FillInBlanks -> this.toDomainModel()
        is QuestionDto.AnswerOptions -> this.toDomainModel()
        is QuestionDto.QuestionAnswerPairs -> this.toDomainModel()
        is QuestionDto.StepByStep -> this.toDomainModel()
    }
}

fun QuestionDto.OpenAnswer.toDomainModel(): Question.OpenAnswer {
    return Question.OpenAnswer(id = id, difficulty = difficulty, text = text)
}

fun QuestionDto.FillInBlanks.toDomainModel(): Question.FillInBlanks {
    return Question.FillInBlanks(id = id, difficulty = difficulty, text = text)
}

fun QuestionDto.AnswerOptions.toDomainModel(): Question.AnswerOptions {
    return Question.AnswerOptions(
        id = id,
        difficulty = difficulty,
        text = text,
        options = options.map { it.toDomainModel() }
    )
}

fun QuestionDto.QuestionAnswerPairs.toDomainModel(): Question.QuestionAnswerPairs {
    return Question.QuestionAnswerPairs(
        id = id,
        difficulty = difficulty,
        text = text,
        questionPairs = questionPairs.map { it.toDomainModel() },
        answerPairs = answerPairs.map { it.toDomainModel() }
    )
}

fun QuestionDto.StepByStep.toDomainModel(): Question.StepByStep {
    return Question.StepByStep(id = id, text = text)
}

fun CorrectAnswerDto.toDomainModel(): CorrectAnswer {
    return when (this) {
        is CorrectAnswerDto.OpenAnswers -> this.toDomainModel()
        is CorrectAnswerDto.Blanks -> this.toDomainModel()
        is CorrectAnswerDto.Option -> this.toDomainModel()
        is CorrectAnswerDto.StepAnswer -> this.toDomainModel()
    }
}

fun CorrectAnswerDto.OpenAnswers.toDomainModel(): CorrectAnswer.OpenAnswers {
    return CorrectAnswer.OpenAnswers(questionId = questionId, answers = answers)
}

fun CorrectAnswerDto.Blanks.toDomainModel(): CorrectAnswer.Blanks {
    return CorrectAnswer.Blanks(questionId = questionId, blanksAnswers = blanksAnswers)
}

fun CorrectAnswerDto.Option.toDomainModel(): CorrectAnswer.Option {
    return CorrectAnswer.Option(questionId = questionId, ids = ids)
}

fun CorrectAnswer.Option.toDto(): CorrectAnswerDto.Option {
    return CorrectAnswerDto.Option(questionId = questionId, ids = ids)
}

fun CorrectAnswerDto.StepAnswer.toDomainModel(): CorrectAnswer.StepAnswer {
    return CorrectAnswer.StepAnswer(questionId = questionId, answer = answer)
}

fun AnswerTextDto.toDomainModel(): AnswerText {
    return AnswerText(
        id = id,
        text = text
    )
}
