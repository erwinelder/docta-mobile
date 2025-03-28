package cz.cvut.docta.di

import com.russhwolf.settings.Settings
import cz.cvut.docta.core.data.database.AppDatabase
import cz.cvut.docta.core.data.database.getLocalDatabaseBuilder
import cz.cvut.docta.core.data.database.getRoomDatabase
import cz.cvut.docta.core.data.preferences.getSecureStorage
import org.koin.dsl.module

actual val platformModule = module {

    /* ---------- Database ---------- */

    single<AppDatabase> {
        getRoomDatabase(
            builder = getLocalDatabaseBuilder(context = get())
        )
    }

    /* ---------- Preferences ---------- */

    single<Settings> {
        getSecureStorage(context = get())
    }

}