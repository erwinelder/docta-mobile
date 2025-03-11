package cz.cvut.docta.courseEditing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.SaveCourseDraftUseCase
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import cz.cvut.docta.sectionEditing.domain.usecase.GetCourseDraftSectionsUseCase
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


    private val _sections = MutableStateFlow<List<SectionDraft>>(emptyList())
    val sections = _sections.asStateFlow()

    private fun applySections(sections: List<SectionDraft>) {
        _sections.update { sections }
    }

    fun fetchCourseDraftSections(courseCode: String) {
        viewModelScope.launch {
            val sections = getCourseDraftSectionsUseCase.execute(courseCode)
            applySections(sections = sections)
        }
    }


    fun fetchCourseDraftData(courseCode: String) {
        viewModelScope.launch {
            val courseDraft = getCourseDraftUseCase.execute(courseCode) ?: return@launch
            changeCourseName(courseDraft.name)
            changeCourseLocale(courseDraft.locale.langCode)
        }
    }

    private fun getCourseDraft(courseCode: String): CourseDraft? {
        val locale = CourseLocale.fromLangCode(courseLocale.value) ?: return null

        return CourseDraft(
            code = courseCode,
            name = courseName.value,
            locale = locale
        )
    }


    fun saveCourseDraftToDatabase(courseCode: String) {
        val courseDraft = getCourseDraft(courseCode = courseCode) ?: return
        viewModelScope.launch {
            saveCourseDraftUseCase.execute(courseDraft = courseDraft)
        }
    }

}