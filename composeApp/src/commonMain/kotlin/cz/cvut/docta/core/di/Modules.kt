package cz.cvut.docta.core.di

import cz.cvut.docta.course.data.local.source.CourseLocalDataSource
import cz.cvut.docta.course.data.local.source.courseLocalDataSourceFactory
import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.data.repository.CourseRepositoryImpl
import cz.cvut.docta.course.domain.usecase.GetAllLightweightCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetAllLightweightCoursesUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val useCaseModule = module {
    single<GetAllLightweightCoursesUseCase> {
        GetAllLightweightCoursesUseCaseImpl(courseRepository = get())
    }
}

val localDataSourceModule = module {
    single<CourseLocalDataSource> {
        courseLocalDataSourceFactory(appLocalDatabase = get())
    }
}

val repositoryModule = module {
    single<CourseRepository> {
        CourseRepositoryImpl(localSource = get())
    }
}