package com.harnick.troupetent.data.remote.dto.bandcamp

data class CollectionSummaryEntity(
  val username: String,
  val fan_id: Long,
  val item_count: Long  // Collects album count for later lookup
)