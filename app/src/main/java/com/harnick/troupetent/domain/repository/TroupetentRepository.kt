package com.harnick.troupetent.domain.repository

import android.content.Context
import com.harnick.troupetent.domain.model.Album
import com.harnick.troupetent.domain.model.Track
import com.harnick.troupetent.util.Resource
import kotlinx.coroutines.flow.Flow

interface TroupetentRepository {
  suspend fun getAlbums(
    context: Context,
	fetchFromRemote: Boolean,
  ): Flow<Resource<List<Album>>>

  suspend fun getAlbumTracks(): Flow<Resource<List<Track>>>

  suspend fun searchData(query: String) {
// TODO: SEARCH TRACKS AND ALBUMS. FILTERS?
  }
}