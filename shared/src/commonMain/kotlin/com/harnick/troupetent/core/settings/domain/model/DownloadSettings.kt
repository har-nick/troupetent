package com.harnick.troupetent.core.settings.domain.model

import com.harnick.troupetent.core.api.domain.model.Codec

data class DownloadSettings(
    val downloadFormat: DownloadFormat,
    val maxDownloadSpeed: Int = 10,
    val maxDownloadStreams: Int = 1,
)

sealed class DownloadFormat(val codec: Codec?, val displayName: String) : Setting(
    description = "The default format your music will be downloaded in",
    title = "Download Format"
) {
    object ASK : DownloadFormat(null, "Ask when you download")
    object AAC : DownloadFormat(Codec.AAC, "AAC")
    object AIFF : DownloadFormat(Codec.AIFF, "AIFF")
    object ALAC : DownloadFormat(Codec.ALAC, "ALAC")
    object FLAC : DownloadFormat(Codec.FLAC, "FLAC")
    object MP3320 : DownloadFormat(Codec.MP3320, "MP3 (320kbps)")
    object MP3V0 : DownloadFormat(Codec.MP3V0, "MP3 (VBR V0)")
    object OGG : DownloadFormat(Codec.OGG, "Ogg Vorbis")
    object WAV : DownloadFormat(Codec.WAV, "WAV")
}