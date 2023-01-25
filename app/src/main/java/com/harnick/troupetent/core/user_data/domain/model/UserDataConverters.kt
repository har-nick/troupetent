package com.harnick.troupetent.core.user_data.domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserDataConverters {
	@TypeConverter
	fun fromTokenPair(pair: Pair<ByteArray, ByteArray>): String {
		return Json.encodeToString(pair)
	}
	
	@TypeConverter
	fun toTokenPair(serializedPair: String): Pair<ByteArray, ByteArray> {
		return Json.decodeFromString(serializedPair)
	}
}