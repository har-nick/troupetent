package uk.co.harnick.troupetent.core.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import uk.co.harnick.troupetent.core.localstorage.data.local.DriverFactory
import uk.co.harnick.troupetent.core.localstorage.data.local.createLocalStorage

actual val platformModule = module {
    single { createLocalStorage(DriverFactory(androidApplication())) }
}
