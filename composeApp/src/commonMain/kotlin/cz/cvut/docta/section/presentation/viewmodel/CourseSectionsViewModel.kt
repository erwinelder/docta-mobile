package cz.cvut.docta.section.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.Course
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.section.domain.model.SectionProgressState
import cz.cvut.docta.section.domain.model.SectionWithStatistics
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionsWithUserStatsUseCase
import cz.cvut.docta.section.domain.usecase.GetSectionsWithUserStatsUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseSectionsViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val getSectionsWithUserStatsUseCase: GetSectionsWithUserStatsUseCase,
    private val getCourseSectionsUseCase: GetCourseSectionsUseCase //replace to new use case with stats + di (paste in di)
) : ViewModel() {

    private val _course = MutableStateFlow<Course?>(null)
    val course = _course.asStateFlow()

    private suspend fun fetchCourse(courseCode: String) {
        _course.update {
            getCourseUseCase.execute(courseCode = courseCode)
        }
    }

    private val _sectionList = MutableStateFlow<List<SectionProgressState>>(emptyList())
    val sectionList = _sectionList.asStateFlow()

    private suspend fun fetchCourseSections() {
        val courseCode = course.value?.code ?: return

        _sectionList.update {
            getSectionsWithUserStatsUseCase.execute(courseCode = courseCode)
        }
    }


    fun fetchData(courseCode: String) {
        viewModelScope.launch {
            fetchCourse(courseCode = courseCode)
            fetchCourseSections()
        }
    }
}