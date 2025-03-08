package cz.cvut.docta.course.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses = _courses.asStateFlow()

    private fun fetchAllCourses() {
        viewModelScope.launch {
            _courses.update {
                // TODO: Replace with real data of user's chosen courses
                getCoursesUseCase.execute(codes = listOf("nss", "dsa", "zan"))
            }
        }
    }


    init {
        fetchAllCourses()
    }

}