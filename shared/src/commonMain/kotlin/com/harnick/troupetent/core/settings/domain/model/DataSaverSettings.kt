package com.harnick.troupetent.core.settings.domain.model

data class DataSaverSettings(
    val maxDownloadSpeed: Int = 1,
    val maxDownloadStreams: Int = 1
)

//  Syncing rate
//  Image size