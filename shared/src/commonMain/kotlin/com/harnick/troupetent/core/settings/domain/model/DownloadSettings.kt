package com.harnick.troupetent.core.settings.domain.model

import Localisation
import com.harnick.troupetent.core.api.domain.model.Codec
import com.harnick.troupetent.core.settings.domain.model.DownloadSettings.DefaultValues.defaultDownloadFormat
import com.harnick.troupetent.core.settings.domain.model.DownloadSettings.DefaultValues.defaultMaxConcurrentDownloads
import kotlinx.serialization.Serializable

@Serializable
data class DownloadSettings(
    val downloadFormat: DownloadFormatSetting = defaultDownloadFormat,
    val maxConcurrentDownloads: MaxConcurrentDownloadsSetting = defaultMaxConcurrentDownloads
) : SettingsCollection {
    private object DefaultValues {
        val defaultDownloadFormat = DownloadFormatSetting(DownloadFormat.MP3V0)
        val defaultMaxConcurrentDownloads = MaxConcurrentDownloadsSetting(3)
    }
}

@Serializable
class DownloadFormatSetting(override val value: DownloadFormat) : Setting<DownloadFormat> {
    override val title = Localisation.settingDownloadFormatTitle.toString()
    override val description = ""
}

@Serializable
class MaxConcurrentDownloadsSetting(override val value: Int) : Setting<Int> {
    override val title = Localisation.settingMaxConcurrentDownloadsTitle.toString()
    override val description = Localisation.settingMaxConcurrentDownloadsDescription.toString()
}

@Serializable
sealed class DownloadFormat(val codec: Codec?, val displayName: String) {
    object ASK : DownloadFormat(null, Localisation.settingDownloadFormatAskPrompt.toString())
    object AAC : DownloadFormat(Codec.AAC, "AAC")
    object AIFF : DownloadFormat(Codec.AIFF, "AIFF")
    object ALAC : DownloadFormat(Codec.ALAC, "ALAC")
    object FLAC : DownloadFormat(Codec.FLAC, "FLAC")
    object MP3320 : DownloadFormat(Codec.MP3320, "MP3 (320kbps)")
    object MP3V0 : DownloadFormat(Codec.MP3V0, "MP3 (VBR V0)")
    object OGG : DownloadFormat(Codec.OGG, "Ogg Vorbis")
    object WAV : DownloadFormat(Codec.WAV, "WAV")
}
