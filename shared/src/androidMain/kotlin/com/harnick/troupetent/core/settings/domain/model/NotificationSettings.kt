package com.harnick.troupetent.core.settings.domain.model

import com.harnick.troupetent.core.settings.domain.model.NotificationSettings.DefaultValues.defaultShowNotificationOnTransition
import com.harnick.troupetent.core.settings.domain.model.NotificationSettings.DefaultValues.defaultShowPersistentPlayerNotification
import kotlinx.serialization.Serializable

@Serializable
actual data class NotificationSettings(
    val showNotificationOnTransition: ShowNotificationOnTransitionSetting =
        defaultShowNotificationOnTransition,
    val showPersistentPlayerNotification: ShowNotificationOnTransitionSetting =
        defaultShowPersistentPlayerNotification
) : SettingsCollection {
    private object DefaultValues {
        val defaultShowNotificationOnTransition = ShowNotificationOnTransitionSetting(false)
        val defaultShowPersistentPlayerNotification = ShowNotificationOnTransitionSetting(true)
    }
}

@Serializable
class ShowNotificationOnTransitionSetting(override val value: Boolean) : Setting<Boolean> {
    override val title = ""
    override val description = ""
}

@Serializable
class ShowPersistentPlayerNotification(override val value: Boolean) : Setting<Boolean> {
    override val title = ""
    override val description = ""
}
