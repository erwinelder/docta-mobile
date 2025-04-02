package cz.cvut.docta.errorHandling.domain.model.result

enum class LessonSessionError : Error {
    LessonSessionIsEmpty,
    AnswerCheckError
}