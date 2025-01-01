package cz.cvut.docta.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule, courseModule, sectionModule, lessonModule, questionModule, answerModule
        )
    }
}