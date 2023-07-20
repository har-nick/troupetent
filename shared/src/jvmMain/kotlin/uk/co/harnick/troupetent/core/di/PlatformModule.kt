package uk.co.harnick.troupetent.core.di

import org.koin.dsl.module
import uk.co.harnick.troupetent.core.localstorage.data.local.DriverFactory
import uk.co.harnick.troupetent.core.localstorage.data.local.createLocalStorage

actual val platformModule = module {
    single { createLocalStorage(DriverFactory()) }
}
