package cz.cvut.docta.lessonSession.presentation.viewmodel.lesson

import androidx.lifecycle.ViewModel
import cz.cvut.docta.lesson.presentation.model.LessonProgression
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LessonProgressViewModel : ViewModel() {

    private val _progression: MutableStateFlow<LessonProgression?> = MutableStateFlow(null)
    val progression = _progression.asStateFlow()

    fun setProgression(questionCount: Int) {
        if (questionCount <= 0) return

        _progression.update {
            LessonProgression(questionCount = questionCount)
        }
    }

    fun incrementProgression() {
        _progression.update {
            it?.increment()
        }
    }

    fun resetProgression() {
        _progression.update { null }
    }

}