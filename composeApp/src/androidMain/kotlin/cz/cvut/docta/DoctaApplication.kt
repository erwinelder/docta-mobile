package cz.cvut.docta

import android.app.Application
import cz.cvut.docta.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class DoctaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DoctaApplication)
        }
    }
}