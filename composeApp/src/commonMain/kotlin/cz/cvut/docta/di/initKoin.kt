package cz.cvut.docta.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule, authModule, coreModule,
            courseModule, sectionModule, lessonModule, lessonSessionModule,
            achievementModule, leaderboardModule
        )
    }
}