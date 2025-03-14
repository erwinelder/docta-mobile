package cz.cvut.docta.di

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.core.domain.usecase.SaveTestCoursesToDatabaseUseCase
import cz.cvut.docta.core.domain.usecase.saveTestCoursesToDatabaseUseCaseFactory
import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.local.source.courseLocalDataSourceFactory
import cz.cvut.docta.course.data.remote.source.CourseRemoteDataSource
import cz.cvut.docta.course.data.remote.source.courseRemoteDataSourceFactory
import cz.cvut.docta.course.data.repository.CourseRemoteRepository
import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCaseImpl
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.course.domain.usecase.GetCourseUseCaseImpl
import cz.cvut.docta.course.domain.usecase.SearchForCourseUseCase
import cz.cvut.docta.course.domain.usecase.SearchForCourseUseCaseImpl
import cz.cvut.docta.course.presentation.viewModel.AddNewCourseViewModel
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import cz.cvut.docta.courseEditing.data.local.source.CourseDraftLocalDataSource
import cz.cvut.docta.courseEditing.data.local.source.courseDraftLocalDataSourceFactory
import cz.cvut.docta.courseEditing.data.repository.CourseDraftRepository
import cz.cvut.docta.courseEditing.data.repository.CourseDraftRepositoryImpl
import cz.cvut.docta.courseEditing.domain.usecase.GetCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.GetCourseDraftUseCaseImpl
import cz.cvut.docta.courseEditing.domain.usecase.SaveCourseDraftUseCase
import cz.cvut.docta.courseEditing.domain.usecase.SaveCourseDraftUseCaseImpl
import cz.cvut.docta.courseEditing.presentation.viewmodel.CourseDraftViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseModule = module {

    /* ---------- Data Sources ---------- */

    single<CourseLocalDataSource> {
        courseLocalDataSourceFactory(appLocalDatabase = get())
    }
    single<CourseRemoteDataSource> {
        courseRemoteDataSourceFactory(appRemoteDatabase = get())
    }

    single<CourseDraftLocalDataSource> {
        courseDraftLocalDataSourceFactory(appLocalDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<CourseRepository> {
//        CourseRepositoryImpl(localSource = get(), remoteSource = get())
        CourseRemoteRepository()
    }

    single<CourseDraftRepository> {
        CourseDraftRepositoryImpl(localSource = get())
    }

    /* ---------- Use Cases ---------- */

    single<GetAllCoursesUseCase> {
        GetAllCoursesUseCaseImpl(courseRepository = get())
    }
    single<GetCourseUseCase> {
        GetCourseUseCaseImpl(courseRepository = get())
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
    single<SearchForCourseUseCase> {
        SearchForCourseUseCaseImpl(courseRepository = get())
    }

    // Temporary use case
    single<SaveTestCoursesToDatabaseUseCase> {
        saveTestCoursesToDatabaseUseCaseFactory(appRemoteDatabase = get())
    }

    /* ---------- View Models ---------- */

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
    viewModel {
        AddNewCourseViewModel(
            searchForCourseUseCase = get()
        )
    }

    /* ---------- Other ---------- */

    single {
        CourseContext()
    }

}