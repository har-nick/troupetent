package uk.co.harnick.troupetent.core.settings.domain

interface Setting<T> {
    val title: String
    val description: String?
    val value: T
}