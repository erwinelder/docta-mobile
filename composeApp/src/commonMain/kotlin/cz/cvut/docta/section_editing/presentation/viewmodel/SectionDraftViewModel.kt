package cz.cvut.docta.section_editing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.section_editing.domain.model.SectionDraft
import cz.cvut.docta.section_editing.domain.usecase.GetSectionDraftUseCase
import cz.cvut.docta.section_editing.domain.usecase.SaveSectionDraftUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SectionDraftViewModel(
    private val getSectionDraftUseCase: GetSectionDraftUseCase,
    private val saveSectionDraftUseCase: SaveSectionDraftUseCase
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
}