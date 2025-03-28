package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.model.LessonWithProgress
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsWithStatisticsUseCase
import cz.cvut.docta.section.domain.model.Section
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
    private val sectionId: Int,
    private val getSectionUseCase: GetSectionUseCase,
    private val getSectionLessonsWithStatisticsUseCase: GetSectionLessonsWithStatisticsUseCase
) : ViewModel() {

    private val _section = MutableStateFlow<Section?>(null)
    val section = _section.asStateFlow()

    private fun fetchSection() {
        viewModelScope.launch {
            _section.update {
                getSectionUseCase.execute(sectionId = sectionId)
            }
        }
    }


    private val _lessonFilterType = MutableStateFlow<LessonFilterType?>(null)
    val lessonFilterType = _lessonFilterType.asStateFlow()

    fun setLessonFilterType(filterType: LessonFilterType?) {
        _lessonFilterType.update { filterType }
    }


    private val _sectionLessons = MutableStateFlow<List<LessonWithProgress>>(emptyList())
    val sectionLessons: StateFlow<List<LessonWithProgress>> = combine(
        _sectionLessons,
        lessonFilterType
    ) { lessons, filterType ->
        lessons
            .filter { lesson ->
                when (filterType) {
                    LessonFilterType.OneStepQuestions -> lesson is LessonWithProgress.Default
                    LessonFilterType.StepByStep -> lesson is LessonWithProgress.StepByStep
                    LessonFilterType.NotDone -> !lesson.completed
                    LessonFilterType.Tests -> lesson is LessonWithProgress.Test
                    null -> true
                }
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    private fun fetchSectionLessons() {
        setRequestLoadingState()

        viewModelScope.launch {
            _sectionLessons.update {
                getSectionLessonsWithStatisticsUseCase.execute(sectionId = sectionId)
            }
            resetResultState()
        }
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.loading_lessons)
        }
    }

    fun resetResultState() {
        _requestState.update { null }
    }


    init {
        fetchSection()
        fetchSectionLessons()
    }

}