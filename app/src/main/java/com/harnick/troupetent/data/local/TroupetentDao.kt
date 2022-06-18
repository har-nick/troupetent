package com.harnick.troupetent.data.local

import com.harnick.troupetent.domain.model.Album
import com.harnick.troupetent.domain.model.Track

// TODO: LOCAL FILE INTERACTIONS GO HERE

interface TroupetentDao {
  suspend fun cacheAlbumData(albumID: Long) {

  }

  suspend fun cacheTrackData(albumID: Long, trackID: Long) {

  }

  suspend fun cacheTrackFile(albumID: Long, trackID: Long) {

  }

  suspend fun loadLocalAlbum(albumID: Long): Album {

  }

  suspend fun loadLocalTrack(albumID: Long, trackID: Long): Track {

  }

  suspend fun deleteAlbum(albumID: Long) {
// TODO: Emit Resource.Deleting with a message of [Deleting X of Y files]
  }

  suspend fun deleteTrack(albumID: Long, trackID: Long) {
// TODO: Emit Resource.Deleting
  }
}