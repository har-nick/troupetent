package com.harnick.troupetent.core.settings.domain.model

interface Setting<T> {
    val title: String
    val description: String
    val value: T
}