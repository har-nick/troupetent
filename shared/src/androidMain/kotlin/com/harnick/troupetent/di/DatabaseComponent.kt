package com.harnick.troupetent.di

import android.app.Application
import com.harnick.troupetent.core.util.DriverFactory
import com.harnick.troupetent.core.util.createDatabase
import com.harnick.troupetent.database.PersistentStorage
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope annotation class Singleton

@Singleton
@Component
actual abstract class DatabaseComponent(@get:Provides val appContext: Application) {
    @Provides actual fun createDb(): PersistentStorage = createDatabase(DriverFactory(appContext))
}
