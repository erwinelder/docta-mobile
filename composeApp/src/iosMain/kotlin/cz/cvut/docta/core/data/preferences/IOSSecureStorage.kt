package cz.cvut.docta.core.data.preferences

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings

@OptIn(ExperimentalSettingsImplementation::class)
fun getSecureStorage(): Settings {
    return KeychainSettings(service = "cz.cvut.docta")
}