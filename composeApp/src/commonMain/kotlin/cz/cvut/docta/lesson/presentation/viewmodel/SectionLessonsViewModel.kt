package cz.cvut.docta.lesson.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.lesson.domain.model.Lesson
import cz.cvut.docta.lesson.domain.model.LessonDifficulty
import cz.cvut.docta.lesson.domain.model.LessonFilterType
import cz.cvut.docta.lesson.domain.usecase.GetSectionLessonsUseCase
import cz.cvut.docta.section.domain.model.SectionLightweight
import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SectionLessonsViewModel(
    private val getSectionUseCase: GetSectionUseCase,
    private val getSectionLessonsUseCase: GetSectionLessonsUseCase
) : ViewModel() {

    private val _section: MutableStateFlow<SectionLightweight?> = MutableStateFlow(null)
    val section = _section.asStateFlow()

    private suspend fun fetchSection(sectionId: Long) {
        _section.update {
            getSectionUseCase.execute(sectionId = sectionId)
        }
    }


    private val _sectionLessons: MutableStateFlow<List<Lesson>> = MutableStateFlow(emptyList())
    val sectionLessons = _sectionLessons.asStateFlow()

    private suspend fun fetchSectionLessons() {
        val sectionId = section.value?.id ?: return

        _sectionLessons.update {
            getSectionLessonsUseCase.execute(sectionId = sectionId)
        }
    }


    private val _lessonDifficulty: MutableStateFlow<LessonDifficulty?> = MutableStateFlow(null)
    val lessonDifficulty = _lessonDifficulty.asStateFlow()

    fun setLessonDifficulty(difficulty: LessonDifficulty?) {
        _lessonDifficulty.update { difficulty }
    }


    private val _lessonFilterType: MutableStateFlow<LessonFilterType?> = MutableStateFlow(null)
    val lessonFilterType = _lessonFilterType.asStateFlow()

    fun setLessonFilterType(filterType: LessonFilterType?) {
        _lessonFilterType.update { filterType }
    }


    fun fetchData(sectionId: Long) {
        viewModelScope.launch {
            fetchSection(sectionId = sectionId)
            fetchSectionLessons()
        }
    }

}