package cz.cvut.docta.di

import cz.cvut.docta.core.data.database.AppLocalDatabase
import cz.cvut.docta.core.data.database.getLocalDatabaseBuilder
import cz.cvut.docta.core.data.database.getRoomLocalDatabase
import org.koin.dsl.module

actual val platformModule = module {

    /* ---------- Database ---------- */

    single<AppLocalDatabase> {
        getRoomLocalDatabase(builder = getLocalDatabaseBuilder())
    }

    /* ---------- Shared Preferences ---------- */



}