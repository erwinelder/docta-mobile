package cz.cvut.docta.sectionEditing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lesson.domain.model.LessonDraft
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsDraftsUseCase
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

    private var courseCode = ""


    private val _sectionName = MutableStateFlow("")
    val sectionName = _sectionName.asStateFlow()

    fun changeSectionName(name: String) {
        _sectionName.update { name }
    }


    private val _lessons = MutableStateFlow<List<LessonDraft>>(emptyList())
    val lessons = _lessons.asStateFlow()

    private fun changeLessonsList(lessons: List<LessonDraft>) {
        _lessons.update { lessons }
    }

    fun fetchSectionDraftLessons(sectionId: Long) {
        viewModelScope.launch {
            val lessons = getSectionLessonsDraftsUseCase.execute(sectionId = sectionId)
            changeLessonsList(lessons)
        }
    }


    fun fetchSectionDraftData(sectionId: Long) {
        viewModelScope.launch {
            val sectionDraft = getSectionDraftUseCase.execute(id = sectionId) ?: return@launch
            courseCode = sectionDraft.courseCode
            changeSectionName(name = sectionDraft.name)
        }
    }

    private fun getSectionDraft(sectionId: Long): SectionDraft {
        return SectionDraft(
            courseCode = courseCode,
            id = sectionId,
            name = sectionName.value
        )
    }

    fun saveSectionDraftToDatabase(sectionId: Long) {
        val sectionDraft = getSectionDraft(sectionId)
        viewModelScope.launch {
            saveSectionDraftUseCase.execute(sectionDraft = sectionDraft)
        }
    }

}