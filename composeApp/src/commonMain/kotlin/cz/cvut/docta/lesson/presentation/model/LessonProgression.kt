package cz.cvut.docta.lesson.presentation.model

data class LessonProgression(
    private val questionCount: Int,
    private val step: Int = 0
) {

    fun increment(): LessonProgression {
        return copy(step = step + 1)
    }

    fun getProgress(): Float {
        return step.toFloat() / questionCount
    }

}