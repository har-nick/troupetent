package com.harnick.troupetent.domain.serializers

import androidx.datastore.core.Serializer
import com.harnick.troupetent.data.model.AppSettings
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object AppSettingsSerializer : Serializer<AppSettings> {
	override val defaultValue: AppSettings
		get() = AppSettings()

	override suspend fun readFrom(input: InputStream): AppSettings {
		return try {
			Json.decodeFromString(
				AppSettings.serializer(),
				input.readBytes().decodeToString()
			)
		} catch (e: Exception) {
			defaultValue
		}
	}

	override suspend fun writeTo(t: AppSettings, output: OutputStream) {
		output.write(
			Json.encodeToString(
				AppSettings.serializer(),
				t
			).encodeToByteArray()
		)
	}
}