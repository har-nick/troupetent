package com.harnick.troupetent.data.mapper

import com.harnick.troupetent.data.remote.dto.bandcamp.TrackEntity
import com.harnick.troupetent.domain.model.Track

fun TrackEntity.toTrack(): Track {
  return Track(
	stream_url = stream_url,
	title = title
  )
}