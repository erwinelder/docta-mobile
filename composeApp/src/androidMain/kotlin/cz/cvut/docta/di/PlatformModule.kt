package cz.cvut.docta.di

import cz.cvut.docta.core.data.local.AppLocalDatabase
import cz.cvut.docta.core.data.local.getLocalDatabaseBuilder
import cz.cvut.docta.remote_data.core.getRemoteDatabaseBuilder
import cz.cvut.docta.core.data.local.getRoomLocalDatabase
import cz.cvut.docta.core.data.remote.AppRemoteDatabase
import cz.cvut.docta.core.data.remote.getRoomRemoteDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<AppLocalDatabase> {
        getRoomLocalDatabase(
            builder = getLocalDatabaseBuilder(context = get())
        )
    }
    single<AppRemoteDatabase> {
        getRoomRemoteDatabase(
            builder = getRemoteDatabaseBuilder(context = get())
        )
    }
}