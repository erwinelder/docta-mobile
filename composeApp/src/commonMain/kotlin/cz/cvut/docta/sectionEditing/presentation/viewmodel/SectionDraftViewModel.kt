package cz.cvut.docta.sectionEditing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCase
import cz.cvut.docta.section.domain.model.Section
import cz.cvut.docta.sectionEditing.domain.model.SectionDraft
import cz.cvut.docta.sectionEditing.domain.usecase.GetSectionDraftUseCase
import cz.cvut.docta.sectionEditing.domain.usecase.SaveSectionDraftUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SectionDraftViewModel(
    private val getSectionDraftUseCase: GetSectionDraftUseCase,
    private val saveSectionDraftUseCase: SaveSectionDraftUseCase,
    private val getSectionLessonsDraftsUseCase: GetSectionLessonsDraftsUseCase
) : ViewModel() {

    private val _sectionName = MutableStateFlow("")
    val sectionName = _sectionName.asStateFlow()

    private val _courseCode = MutableStateFlow("")
    val courseCode = _courseCode.asStateFlow()

    fun changeSectionName(name: String) {
        _sectionName.update { name }
    }

    fun saveSectionDraftToDatabase(sectionId: Long) {
        val sectionDraft = getSectionDraft(sectionId)
        viewModelScope.launch {
            saveSectionDraftUseCase.execute(sectionDraft)
        }
    }

    private fun getSectionDraft(sectionId: Long): SectionDraft {
        val courseCodeValue = courseCode.value

        return SectionDraft(
            courseCode = courseCodeValue,
            id = sectionId,
            name = sectionName.value
        )
    }

    fun fetchSectionDraftData(sectionId: Long) {
        viewModelScope.launch {
            val sectionDraft = getSectionDraftUseCase.execute(sectionId) ?: return@launch
            _courseCode.update { sectionDraft.courseCode }
            changeSectionName(sectionDraft.name)
        }
    }
    private val _lessonList = MutableStateFlow<List<LessonDraft>>(emptyList())
    val lessonList = _lessonList.asStateFlow()

    private fun changeLessonsList(lessons: List<LessonDraft>) {
        _lessonList.update { lessons }
    }

    fun fetchSectionDraftLessons(sectionId: Long) {
        viewModelScope.launch {
            val lessons = getSectionLessonsDraftsUseCase.execute(sectionId)
            changeLessonsList(lessons)
        }
    }
}