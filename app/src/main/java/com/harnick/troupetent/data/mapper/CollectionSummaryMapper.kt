package com.harnick.troupetent.data.mapper

import com.harnick.troupetent.data.remote.dto.bandcamp.CollectionSummaryEntity
import com.harnick.troupetent.domain.model.CollectionSummary

fun CollectionSummaryEntity.toCollectionSummary(): CollectionSummary {
  return CollectionSummary(
	username = username
  )
}