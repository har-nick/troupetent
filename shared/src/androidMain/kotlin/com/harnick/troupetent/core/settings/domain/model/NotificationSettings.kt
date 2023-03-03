package com.harnick.troupetent.core.settings.domain.model

actual data class NotificationSettings(
    val showNotificationOnTransition: Boolean = false,
    val showPersistentPlayerNotification: Boolean = true
)
