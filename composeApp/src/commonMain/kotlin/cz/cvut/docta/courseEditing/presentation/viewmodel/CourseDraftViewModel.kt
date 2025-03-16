package cz.cvut.docta.courseEditing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.courseEditing.domain.model.CourseDraft
import cz.cvut.docta.courseEditing.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.SaveCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.SaveRemoteCourseDraftUseCase
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import cz.cvut.docta.sectionEditing.domain.usecase.GetCourseDraftSectionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDraftViewModel(
    private val courseCode: String,
    private val getCourseDraftUseCase: GetCourseDraftUseCase,
    private val saveCourseDraftUseCase: SaveCourseDraftUseCase,
    private val saveRemoteCourseDraftUseCase: SaveRemoteCourseDraftUseCase,
    private val getCourseDraftSectionsUseCase: GetCourseDraftSectionsUseCase
) : ViewModel() {

    private val _courseName = MutableStateFlow("")
    val courseName = _courseName.asStateFlow()

    private val _courseLocale = MutableStateFlow("")
    val courseLocale = _courseLocale.asStateFlow()

    private val _sectionList = MutableStateFlow<List<SectionDraft>>(emptyList())
    val sectionList = _sectionList.asStateFlow()

    init {
        viewModelScope.launch {
            val courseDraft = getCourseDraftUseCase.execute(courseCode)
            if (courseDraft != null) {
                changeCourseName(courseDraft.name)
                changeCourseLocale(courseDraft.locale.langCode)
            }

            val sections = getCourseDraftSectionsUseCase.execute(courseCode)
            changeSectionList(sections)
        }
    }

    fun changeCourseName(name: String) {
        _courseName.update { name }
    }

    fun changeCourseLocale(locale: String) {
        _courseLocale.update { locale }
    }
    fun changeSectionList(sections: List<SectionDraft>) {
        _sectionList.update { sections }
    }

    fun saveCourseDraftToDatabase(courseCode: String) {
        val courseDraft = getCourseDraft(courseCode) ?: return
        viewModelScope.launch {
            saveCourseDraftUseCase.execute(courseDraft)
            saveRemoteCourseDraftUseCase.execute(courseDraft)
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
}
