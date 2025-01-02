package cz.cvut.docta.di

import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.local.source.courseLocalDataSourceFactory
import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.data.repository.CourseRepositoryImpl
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCaseTemp
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.course.domain.usecase.GetCourseUseCaseImpl
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.course_draft.data.local.source.CourseDraftLocalDataSource
import cz.cvut.docta.course_draft.data.local.source.courseDraftLocalDataSourceFactory
import cz.cvut.docta.course_draft.data.repository.CourseDraftRepository
import cz.cvut.docta.course_draft.data.repository.CourseDraftRepositoryImpl
import cz.cvut.docta.course_draft.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.course_draft.domain.usecase.GetCourseDraftUseCaseImpl
import cz.cvut.docta.course_draft.domain.usecase.SaveCourseDraftUseCase
import cz.cvut.docta.course_draft.domain.usecase.SaveCourseDraftUseCaseImpl
import cz.cvut.docta.course_draft.presentation.viewmodel.CourseDraftViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseModule = module {

    single<CourseLocalDataSource> {
        courseLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<CourseDraftLocalDataSource> {
        courseDraftLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<CourseRepository> {
        CourseRepositoryImpl(localSource = get())
    }
    single<CourseDraftRepository> {
        CourseDraftRepositoryImpl(localSource = get())
    }

    single<GetAllCoursesUseCase> {
//        GetAllCoursesUseCaseImpl(courseRepository = get())
        GetAllCoursesUseCaseTemp()
    }
    single<GetCourseUseCase> {
        GetCourseUseCaseImpl(courseRepository = get())
//        GetCourseUseCaseTemp()
    }
    single<GetCourseDraftUseCase> {
        GetCourseDraftUseCaseImpl(
            courseRepository = get(),
            courseDraftRepository = get()
        )
    }
    single<SaveCourseDraftUseCase> {
        SaveCourseDraftUseCaseImpl(repository = get())
    }

    viewModel {
        CoursesViewModel(
            getAllCoursesUseCase = get()
        )
    }
    viewModel {
        CourseDraftViewModel(
            getCourseDraftUseCase = get(),
            saveCourseDraftUseCase = get()
        )
    }

}