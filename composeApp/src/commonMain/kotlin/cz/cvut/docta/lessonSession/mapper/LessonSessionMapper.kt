package cz.cvut.docta.lessonSession.mapper

import cz.cvut.docta.lessonSession.domain.model.answer.AnswerText
import cz.cvut.docta.lessonSession.domain.model.answer.CorrectAnswer
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lessonSession.data.model.AnswerTextDto
import cz.cvut.docta.lessonSession.data.model.CorrectAnswerDto
import cz.cvut.docta.lessonSession.data.model.QuestionDifficultyDto
import cz.cvut.docta.lessonSession.data.model.QuestionDto
import cz.cvut.docta.lessonSession.data.model.QuestionWithCorrectAnswersDto
import cz.cvut.docta.lessonSession.data.model.SessionOptionsDto
import cz.cvut.docta.lessonSession.domain.model.question.Question
import cz.cvut.docta.lessonSession.domain.model.QuestionWithCorrectAnswers
import cz.cvut.docta.lessonSession.domain.model.SessionOptions


fun LessonDifficulty.toQuestionDifficultyDto(): QuestionDifficultyDto {
    return when (this) {
        LessonDifficulty.Easy -> QuestionDifficultyDto.Easy
        LessonDifficulty.Medium -> QuestionDifficultyDto.Medium
        LessonDifficulty.Hard -> QuestionDifficultyDto.Hard
    }
}


fun LessonWithProgress.getSessionOptions(): SessionOptions {
    return when (this) {
        is LessonWithProgress.Default -> SessionOptions.Default(
            lessonId = this.id,
            difficulty = this.difficulty,
            matchAllTags = this.matchAllTags
        )
        is LessonWithProgress.Test -> SessionOptions.Default(
            lessonId = this.id,
            difficulty = LessonDifficulty.Hard, // TODO
            matchAllTags = this.matchAllTags
        )
        is LessonWithProgress.StepByStep -> SessionOptions.StepByStep(lessonId = this.id)
    }
}


fun SessionOptions.toDto(courseCode: String): SessionOptionsDto {
    return when (this) {
        is SessionOptions.Default -> SessionOptionsDto.Default(
            courseCode = courseCode,
            lessonId = lessonId,
            difficulty = difficulty.toQuestionDifficultyDto(),
            matchAllTags = matchAllTags
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
            answer = this.answer.toDomainModel()
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
    return CorrectAnswer.Option(questionId = questionId, id = id)
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
