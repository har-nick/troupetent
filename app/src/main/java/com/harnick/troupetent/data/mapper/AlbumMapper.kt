package com.harnick.troupetent.data.mapper

import com.harnick.troupetent.data.remote.dto.bandcamp.AlbumEntity
import com.harnick.troupetent.domain.model.Album

fun AlbumEntity.toAlbum(): Album {
  return Album(
	title = title,
	band_name = band_name
  )
}