package cz.cvut.docta.section.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import cz.cvut.docta.section.domain.model.SectionWithProgress
import cz.cvut.docta.section.domain.usecase.GetSectionsWithProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseSectionsViewModel(
    private val courseCode: String,
    private val getCourseUseCase: GetCourseUseCase,
    private val getSectionsWithProgressUseCase: GetSectionsWithProgressUseCase
) : ViewModel() {

    private val _course = MutableStateFlow<Course?>(null)
    val course = _course.asStateFlow()

    private fun fetchCourse() {
        viewModelScope.launch {
            _course.update {
                getCourseUseCase.execute(courseCode = courseCode)
            }
        }
    }


    private val _sections = MutableStateFlow<List<SectionWithProgress>>(emptyList())
    val sections = _sections.asStateFlow()

    private fun fetchCourseSections() {
        setRequestLoadingState()

        viewModelScope.launch {
            _sections.update {
                getSectionsWithProgressUseCase.execute(courseCode = courseCode)
            }
            resetResultState()
        }
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.loading_sections)
        }
    }

    fun resetResultState() {
        _requestState.update { null }
    }


    init {
        fetchCourse()
        fetchCourseSections()
    }

}