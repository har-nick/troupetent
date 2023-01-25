package com.harnick.troupetent.library.data.dto.collection_items

import kotlinx.serialization.Serializable

@Serializable
data class MerchSnapshotEntity(
	val merch_art_0: Long,
	val merch_art_1: Long?,
	val merch_art_2: Long?,
	val package_type_id: Long?
)