package cz.cvut.docta.section.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLightweight
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.domain.model.SectionStatistics
import cz.cvut.docta.section.domain.usecase.GetCourseSectionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseSectionsViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val getCourseSectionsUseCase: GetCourseSectionsUseCase
) : ViewModel() {

    private val _course = MutableStateFlow<CourseLightweight?>(null)
    val course = _course.asStateFlow()

    private suspend fun fetchCourse(courseCode: String) {
        _course.update {
            getCourseUseCase.execute(courseCode = courseCode)
        }
    }


    private val _sectionList = MutableStateFlow<List<Section>>(emptyList())
    val sectionList = _sectionList.asStateFlow()

    private suspend fun fetchCourseSections() {
        val courseCode = course.value?.code ?: return

        _sectionList.update {
            getCourseSectionsUseCase.execute(courseCode = courseCode).takeIf { it.isNotEmpty() }
                ?: getTestSectionList()
        }
    }

    private fun getTestSectionList(): List<Section> {
        return listOf(
            Section(
                id = 1,
                name = "Úvod do matematické analýzy, základní principy kalkulu",
                statistics = SectionStatistics()
            ),
            Section(
                id = 2,
                name = "Reálná čísla, základní matematická terminologie",
                statistics = SectionStatistics()
            ),
            Section(
                id = 3,
                name = "Limita funkce, spojitost.",
                statistics = SectionStatistics()
            ),
            Section(
                id = 4,
                name = "Derivace funkce, její vlastnosti a význam",
                statistics = SectionStatistics()
            ),
            Section(
                id = 5,
                name = "L'Hospitalovo pravidlo, Taylorův polynom",
                statistics = SectionStatistics()
            ),
        )
    }


    fun fetchData(courseCode: String) {
        viewModelScope.launch {
            fetchCourse(courseCode = courseCode)
            fetchCourseSections()
        }
    }

}