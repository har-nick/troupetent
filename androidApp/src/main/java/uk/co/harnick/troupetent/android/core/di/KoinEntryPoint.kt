package uk.co.harnick.troupetent.android.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uk.co.harnick.troupetent.core.di.coroutineModule
import uk.co.harnick.troupetent.core.di.platformModule
import uk.co.harnick.troupetent.core.di.sharedModule

class KoinEntryPoint : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinEntryPoint)
            modules(coroutineModule, sharedModule, platformModule)
        }
    }
}
