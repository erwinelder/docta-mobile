package cz.cvut.docta.course_editing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course.domain.model.CourseLocale
import cz.cvut.docta.course_editing.domain.model.CourseDraft
import cz.cvut.docta.course_editing.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.course_editing.domain.usecase.SaveCourseDraftUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CourseDraftViewModel(
    private val getCourseDraftUseCase: GetCourseDraftUseCase,
    private val saveCourseDraftUseCase: SaveCourseDraftUseCase
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
        val locale = CourseLocale.entries.find { it.name == courseLocale.value } ?: return null

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
            changeCourseLocale(courseDraft.locale.name)
        }
    }

}