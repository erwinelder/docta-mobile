package cz.cvut.docta.course.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLightweight
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase
) : ViewModel() {

    private val _courseList: MutableStateFlow<List<CourseLightweight>> = MutableStateFlow(
        emptyList()
    )
    val courseList = _courseList.asStateFlow()

    private fun fetchAllCourses() {
        viewModelScope.launch {
            getAllCoursesUseCase.execute().collect { courseList ->
                _courseList.update {
                    courseList.takeIf { it.isNotEmpty() } ?: getTestCourseList()
                }
            }
        }
    }

    private fun getTestCourseList(): List<CourseLightweight> {
        return listOf(
            CourseLightweight(
                code = "course_code_1",
                locale = "cs",
                name = "1. Course name"
            ),
            CourseLightweight(
                code = "course_code_2",
                locale = "en",
                name = "2. Course name"
            ),
        )
    }


    init {
        fetchAllCourses()
    }

}