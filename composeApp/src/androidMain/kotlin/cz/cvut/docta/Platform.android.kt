package cz.cvut.docta

import android.os.Build
import cz.cvut.docta.presentation.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()