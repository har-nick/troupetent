package com.harnick.troupetent.library.data.remote.bandcamp.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PackageDetailsEntity(
	@SerialName("band_id")
	val bandId: Long,
	@SerialName("band_name")
	val bandName: String,
	val description: String,
	@SerialName("images")
	val imageEntities: List<ImageEntity>,
	@SerialName("is_live_event")
	val isLiveEvent: Boolean,
	@SerialName("is_subscriber_only")
	val isSubscriberOnly: Boolean,
	@SerialName("item_url")
	val itemUrl: String,
	val killed: Boolean?,
	@SerialName("label_id")
	val labelId: Long?,
	val private: Boolean?,
	val title: String,
	@SerialName("url_hints")
	val urlHints: UrlHintsEntity
)