package cz.cvut.docta.di

import cz.cvut.docta.section.domain.usecase.GetSectionUseCase
import org.koin.dsl.module

val courseSectionModule = module {

    single<GetSectionUseCase> {
        // TODO
    }

}