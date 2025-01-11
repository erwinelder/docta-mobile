package cz.cvut.docta.core.data.model

enum class TableName {
    Course,
    Section,
    Lesson,
    QuestionTag, QuestionTagDefaultLessonAssociation, QuestionTagQuestionAssociation, Question,
    StepByStepQuestion,
    CorrectOpenAnswer, BlankAnswer, AnswerOption,
    QuestionAnswerPairTag, PairTagQuestionAssociation, PairTagPairAssociation, QuestionAnswerPair
}