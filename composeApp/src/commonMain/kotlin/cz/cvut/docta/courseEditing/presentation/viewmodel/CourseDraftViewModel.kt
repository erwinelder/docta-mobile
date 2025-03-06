package cz.cvut.docta.courseEditing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.SaveCourseDraftUseCase
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.section.domain.usecase.GetCourseDraftSectionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDraftViewModel(
    private val getCourseDraftUseCase: GetCourseDraftUseCase,
    private val saveCourseDraftUseCase: SaveCourseDraftUseCase,
    private val getCourseDraftSectionsUseCase: GetCourseDraftSectionsUseCase
) : ViewModel() {

    private val _courseName = MutableStateFlow("")
    val courseName = _courseName.asStateFlow()

    fun changeCourseName(name: String) {
        _courseName.update { name }
    }


    private val _courseLocale = MutableStateFlow("")
    val courseLocale = _courseLocale.asStateFlow()

    fun changeCourseLocale(locale: String) {
        _courseLocale.update { locale }
    }


    fun saveCourseDraftToDatabase(courseCode: String) {
        val courseDraft = getCourseDraft(courseCode) ?: return
        viewModelScope.launch {
            saveCourseDraftUseCase.execute(courseDraft)
        }
    }

    private fun getCourseDraft(courseCode: String): CourseDraft? {
        val locale = CourseLocale.fromString(courseLocale.value) ?: return null

        return CourseDraft(
            code = courseCode,
            name = courseName.value,
            locale = locale
        )
    }


    fun fetchCourseDraftData(courseCode: String) {
        viewModelScope.launch {
            val courseDraft = getCourseDraftUseCase.execute(courseCode) ?: return@launch
            changeCourseName(courseDraft.name)
            changeCourseLocale(courseDraft.locale.langCode)
        }
    }

    private val _sectionList = MutableStateFlow<List<Section>>(emptyList())
    val sectionList = _sectionList.asStateFlow()

    private fun changeSectionList(sections: List<Section>) {
        _sectionList.update { sections }
    }

    fun fetchCourseDraftSections(courseCode: String) {
        viewModelScope.launch {
            val sections = getCourseDraftSectionsUseCase.execute(courseCode)
            changeSectionList(sections)
        }
    }
}