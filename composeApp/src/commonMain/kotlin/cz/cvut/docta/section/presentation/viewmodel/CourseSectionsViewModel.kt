package cz.cvut.docta.section.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.section.domain.model.SectionWithProgress
import cz.cvut.docta.section.domain.usecase.GetSectionsWithProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseSectionsViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val getSectionsWithProgressUseCase: GetSectionsWithProgressUseCase
) : ViewModel() {

    private val _course = MutableStateFlow<Course?>(null)
    val course = _course.asStateFlow()

    private suspend fun fetchCourse(courseCode: String) {
        _course.update {
            getCourseUseCase.execute(courseCode = courseCode)
        }
    }


    private val _sections = MutableStateFlow<List<SectionWithProgress>>(emptyList())
    val sections = _sections.asStateFlow()

    private suspend fun fetchCourseSections() {
        val courseCode = course.value?.code ?: return

        _sections.update {
            getSectionsWithProgressUseCase.execute(courseCode = courseCode)
        }
    }


    fun fetchData(courseCode: String) {
        viewModelScope.launch {
            fetchCourse(courseCode = courseCode)
            fetchCourseSections()
        }
    }

}