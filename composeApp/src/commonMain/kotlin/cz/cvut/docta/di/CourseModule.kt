package cz.cvut.docta.di

import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.local.source.courseLocalDataSourceFactory
import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.data.repository.CourseRepositoryImpl
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetAllCoursesUseCaseImpl
import cz.cvut.docta.course.presentation.viewModel.CoursesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseModule = module {

    single<CourseLocalDataSource> {
        courseLocalDataSourceFactory(appLocalDatabase = get())
    }

    single<CourseRepository> {
        CourseRepositoryImpl(localSource = get())
    }

    single<GetAllCoursesUseCase> {
        GetAllCoursesUseCaseImpl(courseRepository = get())
    }

    viewModel {
        CoursesViewModel(
            getAllCoursesUseCase = get()
        )
    }

}