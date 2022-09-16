package com.harnick.troupetent.di

import android.content.Context
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import java.io.File

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

	@Provides
	@ServiceScoped
	fun provideAudioAttributes(): AudioAttributes =
		AudioAttributes.Builder()
			.setContentType((C.AUDIO_CONTENT_TYPE_MUSIC))
			.setUsage(C.USAGE_MEDIA)
			.build()

	@Provides
	@ServiceScoped
	fun provideExoplayer(
		@ApplicationContext appContext: Context,
		audioAttributes: AudioAttributes
	) = ExoPlayer.Builder(appContext)
		.build()
		.apply {
			setAudioAttributes(audioAttributes, true)
			setHandleAudioBecomingNoisy(true)
		}

	@Provides
	@ServiceScoped
	fun provideCacheDataSourceFactory(
		@ApplicationContext appContext: Context,
		httpDataSource: DefaultHttpDataSource.Factory
	): CacheDataSource.Factory {
		val cacheDir = File(appContext.cacheDir, "bandcamp-music")
		val dbProvider = StandaloneDatabaseProvider(appContext)
		val cache = SimpleCache(cacheDir, NoOpCacheEvictor(), dbProvider)

		return CacheDataSource.Factory().apply {
			setCache(cache)
			setUpstreamDataSourceFactory(httpDataSource)
		}
	}

	@Provides
	@ServiceScoped
	fun provideHttpDataSourceFactory() = DefaultHttpDataSource.Factory()
		.setAllowCrossProtocolRedirects(true)
}