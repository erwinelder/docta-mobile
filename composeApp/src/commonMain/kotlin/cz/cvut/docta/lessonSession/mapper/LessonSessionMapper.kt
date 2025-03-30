package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lessonSession.data.model.AnswerTextDto
import cz.cvut.docta.lessonSession.data.model.CorrectAnswerDto
import cz.cvut.docta.lessonSession.data.model.QuestionDifficultyDto
import cz.cvut.docta.lessonSession.data.model.QuestionDto
import cz.cvut.docta.lessonSession.data.model.QuestionWrapperDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.lessonSession.domain.model.SessionOptions
import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
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


fun QuestionWrapperDto.toDomainModel(): QuestionWithCorrectAnswers {
    return when (this) {
        is QuestionWrapperDto.OpenAnswer -> QuestionWithCorrectAnswers.OpenAnswer(
            question = this.question.toDomainModel(),
            answers = this.answers.toDomainModel()
        )
        is QuestionWrapperDto.FillInBlanks -> QuestionWithCorrectAnswers.FillInBlanks(
            question = this.question.toDomainModel(),
            blanksAnswers = this.blanksAnswers.toDomainModel()
        )
        is QuestionWrapperDto.AnswerOptions -> QuestionWithCorrectAnswers.AnswerOptions(
            question = this.question.toDomainModel(),
            answer = this.answer.toDomainModel()
        )
        is QuestionWrapperDto.QuestionAnswerPairs -> QuestionWithCorrectAnswers.QuestionAnswerPairs(
            question = this.question.toDomainModel()
        )
    }
}


fun QuestionDto.toDomainModel(): Question {
    return when (this) {
        is QuestionDto.OpenAnswer -> this.toDomainModel()
        is QuestionDto.FillInBlanks -> this.toDomainModel()
        is QuestionDto.AnswerOptions -> this.toDomainModel()
        is QuestionDto.QuestionAnswerPairs -> this.toDomainModel()
    }
}

fun QuestionDto.OpenAnswer.toDomainModel(): Question.OpenAnswer {
    return Question.OpenAnswer(id = id, difficulty = difficulty.toDomainModel(), text = text)
}

fun QuestionDto.FillInBlanks.toDomainModel(): Question.FillInBlanks {
    return Question.FillInBlanks(id = id, difficulty = difficulty.toDomainModel(), text = text)
}

fun QuestionDto.AnswerOptions.toDomainModel(): Question.AnswerOptions {
    return Question.AnswerOptions(
        id = id,
        difficulty = difficulty.toDomainModel(),
        text = text,
        options = options.map { it.toDomainModel() }
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


fun CorrectAnswerDto.toDomainModel(): CorrectAnswer {
    return when (this) {
        is CorrectAnswerDto.OpenAnswers -> this.toDomainModel()
        is CorrectAnswerDto.Blanks -> this.toDomainModel()
        is CorrectAnswerDto.Option -> this.toDomainModel()
    }
}

fun CorrectAnswerDto.OpenAnswers.toDomainModel(): CorrectAnswer.OpenAnswers {
    return CorrectAnswer.OpenAnswers(questionId = questionId, answers = answers)
}

fun CorrectAnswerDto.Blanks.toDomainModel(): CorrectAnswer.Blanks {
    return CorrectAnswer.Blanks(questionId = questionId, blanksAnswers = blanksAnswers)
}

fun CorrectAnswerDto.Option.toDomainModel(): CorrectAnswer.Option {
    return CorrectAnswer.Option(questionId = questionId, id = id)
}



fun AnswerTextDto.toDomainModel(): AnswerText {
    return AnswerText(
        id = id,
        text = text
    )
}
