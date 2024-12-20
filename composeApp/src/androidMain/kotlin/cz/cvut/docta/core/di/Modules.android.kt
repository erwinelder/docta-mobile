package cz.cvut.docta.core.di

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.core.data.local.getDatabaseBuilder
import cz.cvut.docta.core.data.local.getRoomDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<AppLocalDatabase> {
        getRoomDatabase(
            builder = getDatabaseBuilder(context = get())
        )
    }
}