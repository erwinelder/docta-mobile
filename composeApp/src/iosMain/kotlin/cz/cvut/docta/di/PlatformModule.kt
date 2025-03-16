package cz.cvut.docta.di

import com.russhwolf.settings.Settings
import cz.cvut.docta.core.data.database.AppLocalDatabase
import cz.cvut.docta.core.data.database.getLocalDatabaseBuilder
import cz.cvut.docta.core.data.database.getRoomLocalDatabase
import cz.cvut.docta.core.data.preferences.getSecureStorage
import org.koin.dsl.module

actual val platformModule = module {

    /* ---------- Database ---------- */

    single<AppLocalDatabase> {
        getRoomLocalDatabase(builder = getLocalDatabaseBuilder())
    }

    /* ---------- Preferences ---------- */

    single<Settings> {
        getSecureStorage()
    }

}