package cz.cvut.docta.course.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.model.CourseSearchState
import cz.cvut.docta.course.domain.usecase.AddCourseToChosenUseCase
import cz.cvut.docta.course.domain.usecase.SearchForCourseUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNewCourseViewModel(
    private val searchForCourseUseCase: SearchForCourseUseCase,
    private val addCourseToChosenUseCase: AddCourseToChosenUseCase
) : ViewModel() {

    private val _courseSearchState = MutableStateFlow<CourseSearchState>(CourseSearchState.Prompt)
    val courseSearchState = _courseSearchState.asStateFlow()

    fun setCourseSearchPromptState() {
        _courseSearchState.update {
            CourseSearchState.Prompt
        }
    }

    private fun setCourseSearchLoadingState() {
        _courseSearchState.update {
            CourseSearchState.Loading(query = query.value)
        }
    }

    private fun setCourseSearchCourseState(query: String, course: Course?) {
        _courseSearchState.update {
            CourseSearchState.SearchedCourse(query = query, course = course)
        }
    }


    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun onQueryChange(query: String) {
        _query.update { query }
    }


    val searchIsAllowed: StateFlow<Boolean> = query
        .map { it.isNotBlank() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false
        )


    private var searchJob: Job? = null

    fun searchForCourse() {
        setCourseSearchLoadingState()
        searchJob = viewModelScope.launch {
            val query = query.value.trim()
            val course = searchForCourseUseCase.execute(query)
            setCourseSearchCourseState(query = query, course = course)
        }
    }

    fun cancelSearch() {
        searchJob?.cancel()
    }


    fun addCourseToChosenCourses(course: Course) {
        viewModelScope.launch {
            addCourseToChosenUseCase.add(code = course.code)
        }
    }

}