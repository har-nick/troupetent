package com.harnick.troupetent.core.util

import com.harnick.troupetent.core.settings.domain.model.ThemeSettings
import com.harnick.troupetent.core.settings.domain.repository.SettingsRepo
import com.harnick.troupetent.core.stats.domain.repository.StatRepo
import com.harnick.troupetent.core.userdata.domain.repository.UserDataRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class Router(
    private val settingsRepo: SettingsRepo,
    private val statRepo: StatRepo,
    private val userDataRepo: UserDataRepo,
) {
    init {
        statRepo.iterateLaunchCount()
    }

    private fun checkSettingsInstance(lifeCycleScope: CoroutineScope) {
        lifeCycleScope.launch {
            statRepo.getLaunchCount().onEach { count ->
                if (count != null && count == 1L) settingsRepo.createSettingsInstance()
            }
        }
    }

    fun getHomeScreen(
        lifeCycleScope: CoroutineScope,
        splashScreenState: Boolean
    ) {
        checkSettingsInstance(lifeCycleScope)

        val themeSettings by settingsRepo.getCollectionAsFlow(ThemeSettings::class).collectAsState()
    }
}
