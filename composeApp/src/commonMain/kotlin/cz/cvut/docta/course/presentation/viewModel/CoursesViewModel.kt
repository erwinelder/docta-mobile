package cz.cvut.docta.course.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.SharedRes
import cz.cvut.docta.course.domain.model.CourseWithProgress
import cz.cvut.docta.course.domain.usecase.GetChosenCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetCoursesWithProgressUseCase
import cz.cvut.docta.errorHandling.presentation.model.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getCoursesWithProgressUseCase: GetCoursesWithProgressUseCase,
    private val getChosenCoursesUseCase: GetChosenCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<List<CourseWithProgress>>(emptyList())
    val courses = _courses.asStateFlow()

    private suspend fun fetchCourses(codes: List<String>) {
        val currentCourses = _courses.value
        val currentCodes = currentCourses.map { it.code }

        if (currentCodes.toSet() == codes.toSet()) return

        val currentCoursesToStay = currentCourses.filter { it.code in codes }
        val currentCodesToStay = currentCoursesToStay.map { it.code }
        val codesToFetch = codes.filterNot { it in currentCodesToStay }
        val fetchedCourses = getCoursesWithProgressUseCase.execute(codes = codesToFetch)
        val finalCourses = currentCoursesToStay + fetchedCourses

        _courses.update {
            codes.mapNotNull { code -> finalCourses.find { it.code == code } }
        }
    }

    private fun fetchAllCourses() {
        setRequestLoadingState()

        viewModelScope.launch {
            getChosenCoursesUseCase.getFlow().collect { codes ->
                fetchCourses(codes = codes)
                resetResultState()
            }
        }
    }

    fun addCourse(course: CourseWithProgress) {
        _courses.update {
            it + course
        }
    }


    private val _requestState = MutableStateFlow<RequestState?>(null)
    val requestState = _requestState.asStateFlow()

    private fun setRequestLoadingState() {
        _requestState.update {
            RequestState.Loading(messageRes = SharedRes.strings.fetching_courses)
        }
    }

    fun resetResultState() {
        _requestState.update { null }
    }


    init {
        fetchAllCourses()
    }

}