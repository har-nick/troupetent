package com.harnick.troupetent.di

import com.harnick.troupetent.core.api.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.core.settings.data.repository.SettingsRepoImpl
import com.harnick.troupetent.core.settings.domain.repository.SettingsRepo
import com.harnick.troupetent.core.stats.data.repository.StatRepoImpl
import com.harnick.troupetent.core.stats.domain.repository.StatRepo
import com.harnick.troupetent.core.userdata.data.repository.UserDataRepoImpl
import com.harnick.troupetent.core.userdata.domain.repository.UserDataRepo
import io.ktor.client.*
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class SharedComponent(@Component val databaseComponent: DatabaseComponent) {
    abstract val bandcampApi: BandcampApi

    @Provides fun ktorClient(): HttpClient = HttpClient()

    @Provides fun SettingsRepoImpl.bind(): SettingsRepo = this

    @Provides fun StatRepoImpl.bind(): StatRepo = this

    @Provides fun UserDataRepoImpl.bind(): UserDataRepo = this
}
