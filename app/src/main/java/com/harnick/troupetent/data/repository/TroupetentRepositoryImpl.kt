package com.harnick.troupetent.data.repository

import android.content.Context
import android.widget.Toast
import com.harnick.troupetent.data.mapper.toAlbum
import com.harnick.troupetent.data.remote.BandcampApi
import com.harnick.troupetent.data.remote.dto.bandcamp.AlbumEntity
import com.harnick.troupetent.domain.model.Album
import com.harnick.troupetent.domain.model.Track
import com.harnick.troupetent.domain.repository.TroupetentRepository
import com.harnick.troupetent.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TroupetentRepositoryImpl @Inject constructor(
  api: BandcampApi
) : TroupetentRepository {
  override suspend fun getAlbums(context: Context, fetchFromRemote: Boolean): Flow<Resource<List<Album>>> {
	return flow {
	  emit(Resource.Loading(true))
	  lateinit var localAlbums: List<AlbumEntity>
	  if (!File(context.cacheDir, "music").isDirectory) {
		Toast.makeText(context, "Albums not synced! Syncing...", Toast.LENGTH_SHORT).show()

	  }

		emit(
		  Resource.Success(
			data = localAlbums.map { it.toAlbum() }
		  )
		)
	}
  }

  override suspend fun getAlbumTracks(): Flow<Resource<List<Track>>> {
	TODO("Not yet implemented")
  }
}