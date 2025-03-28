package cz.cvut.docta.di

import cz.cvut.docta.core.domain.app.CourseContext
import cz.cvut.docta.course.data.repository.ChosenCourseRepository
import cz.cvut.docta.course.data.repository.CourseRepositoryImpl
import cz.cvut.docta.course.data.repository.CourseRepository
import cz.cvut.docta.course.data.repository.chosenCourseRepositoryFactory
import cz.cvut.docta.course.domain.usecase.AddCourseToChosenUseCase
import cz.cvut.docta.course.domain.usecase.AddCourseToChosenUseCaseImpl
import cz.cvut.docta.course.domain.usecase.GetChosenCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetChosenCoursesUseCaseImpl
import cz.cvut.docta.course.domain.usecase.GetCourseUseCase
import cz.cvut.docta.course.domain.usecase.GetCourseUseCaseImpl
import cz.cvut.docta.course.domain.usecase.GetCoursesUseCase
import cz.cvut.docta.course.domain.usecase.GetCoursesUseCaseImpl
import cz.cvut.docta.course.domain.usecase.GetCoursesWithProgressUseCase
import cz.cvut.docta.course.domain.usecase.GetCoursesWithProgressUseCaseImpl
import cz.cvut.docta.course.domain.usecase.SaveChosenCoursesUseCase
import cz.cvut.docta.course.domain.usecase.SaveChosenCoursesUseCaseImpl
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

    single<CourseDraftLocalDataSource> {
        courseDraftLocalDataSourceFactory(appDatabase = get())
    }

    /* ---------- Repositories ---------- */

    single<CourseRepository> {
        CourseRepositoryImpl(userContext = get())
    }

    single<ChosenCourseRepository> {
        chosenCourseRepositoryFactory(appDatabase = get())
    }

    single<CourseDraftRepository> {
        CourseDraftRepositoryImpl(localSource = get())
    }

    /* ---------- Use Cases ---------- */

    single<GetCoursesUseCase> {
        GetCoursesUseCaseImpl(courseRepository = get())
    }
    single<GetCoursesWithProgressUseCase> {
        GetCoursesWithProgressUseCaseImpl(courseRepository = get())
    }
    single<GetCourseUseCase> {
        GetCourseUseCaseImpl(courseRepository = get())
    }

    single<SearchForCourseUseCase> {
        SearchForCourseUseCaseImpl(courseRepository = get())
    }

    single<AddCourseToChosenUseCase> {
        AddCourseToChosenUseCaseImpl(chosenCourseRepository = get())
    }
    single<GetChosenCoursesUseCase> {
        GetChosenCoursesUseCaseImpl(chosenCourseRepository = get())
    }
    single<SaveChosenCoursesUseCase> {
        SaveChosenCoursesUseCaseImpl(chosenCourseRepository = get())
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

    /* ---------- View Models ---------- */

    viewModel {
        CoursesViewModel(
            getCoursesWithProgressUseCase = get(),
            getChosenCoursesUseCase = get()
        )
    }
    
    viewModel {
        AddNewCourseViewModel(
            searchForCourseUseCase = get(),
            addCourseToChosenUseCase = get()
        )
    }
    
    viewModel {
        CourseDraftViewModel(
            getCourseDraftUseCase = get(),
            saveCourseDraftUseCase = get(),
            getCourseDraftSectionsUseCase = get()
        )
    }

    /* ---------- Other ---------- */

    single {
        CourseContext()
    }

}