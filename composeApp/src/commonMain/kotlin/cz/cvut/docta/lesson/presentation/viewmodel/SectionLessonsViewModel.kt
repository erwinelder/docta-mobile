package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCase
import cz.cvut.docta.section.domain.model.SectionLightweight
import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SectionLessonsViewModel(
    private val getSectionUseCase: GetSectionUseCase,
    private val getSectionLessonsWithStatisticsUseCase: GetSectionLessonsWithStatisticsUseCase
) : ViewModel() {

    private val _section = MutableStateFlow<SectionLightweight?>(null)
    val section = _section.asStateFlow()

    private suspend fun fetchSection(sectionId: Long) {
        _section.update {
            getSectionUseCase.execute(sectionId = sectionId)
        }
    }


    private val _lessonFilterType = MutableStateFlow<LessonFilterType?>(null)
    val lessonFilterType = _lessonFilterType.asStateFlow()

    fun setLessonFilterType(filterType: LessonFilterType?) {
        _lessonFilterType.update { filterType }
    }


    private val _lessonDifficulty = MutableStateFlow<LessonDifficulty?>(null)
    val lessonDifficulty = _lessonDifficulty.asStateFlow()

    fun setLessonDifficulty(difficulty: LessonDifficulty?) {
        _lessonDifficulty.update { difficulty }
    }


    private val _sectionLessons = MutableStateFlow<List<Lesson>>(emptyList())
    val sectionLessons: StateFlow<List<Lesson>> = combine(
        _sectionLessons,
        lessonDifficulty,
        lessonFilterType
    ) { lessons, difficulty, filterType ->
        lessons
            .filter { lesson ->
                when (filterType) {
                    LessonFilterType.OneStepQuestions -> lesson is Lesson.Default
                    LessonFilterType.StepByStep -> lesson is Lesson.StepByStep
                    LessonFilterType.NotDone -> !lesson.statistics.isDone
                    LessonFilterType.Tests -> lesson is Lesson.Test
                    null -> true
                }
            }
            .filter { lesson ->
                if (difficulty == null) {
                    true
                } else {
                    when (lesson) {
                        is Lesson.Default -> lesson.difficulty == difficulty
                        is Lesson.StepByStep -> lesson.difficulty == difficulty
                        else -> false
                    }
                }
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    private suspend fun fetchSectionLessons() {
        val sectionId = section.value?.id ?: return

        _sectionLessons.update {
            getSectionLessonsWithStatisticsUseCase.execute(sectionId = sectionId)
        }
    }


    fun fetchData(sectionId: Long) {
        viewModelScope.launch {
            fetchSection(sectionId = sectionId)
            fetchSectionLessons()
        }
    }

}