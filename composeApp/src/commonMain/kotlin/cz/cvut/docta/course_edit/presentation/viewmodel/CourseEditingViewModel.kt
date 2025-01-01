package cz.cvut.docta.course_edit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.docta.course_edit.data.model.CourseEditingEntity
import cz.cvut.docta.course_edit.domain.usecase.CourseEditingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CourseEditingViewModel(private val useCase: CourseEditingUseCase) : ViewModel() {

    private val _courseEditingState = MutableStateFlow<CourseEditingEntity?>(null)
    val courseEditingState: StateFlow<CourseEditingEntity?> = _courseEditingState

    fun loadCourseEditing(courseCode: String) {
        viewModelScope.launch {
            _courseEditingState.value = useCase.getCourseEditing(courseCode)
        }
    }

    fun saveCourseEditing(courseEditingEntity: CourseEditingEntity) {
        viewModelScope.launch {
            useCase.saveCourseEditing(courseEditingEntity)
        }
    }
}