package com.harnick.troupetent.di

import android.content.Context
import coil.ImageLoader
import com.harnick.troupetent.data.remote.bandcamp.BandcampApi
import com.harnick.troupetent.domain.repository.EncRepo
import com.harnick.troupetent.domain.use_cases.*
import com.harnick.troupetent.domain.use_cases.collated.LibraryUseCases
import com.harnick.troupetent.domain.use_cases.collated.LoginUseCases
import com.harnick.troupetent.domain.use_cases.collated.PlayerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	@Singleton
	fun provideCoilImageLoader(
		@ApplicationContext appContext: Context
	): ImageLoader {
		return ImageLoader(appContext)
			.newBuilder()
			.build()
	}
	
	@Provides
	@Singleton
	fun provideLibraryUseCases(
		api: BandcampApi,
		@ApplicationContext appContext: Context,
	): LibraryUseCases {
		return LibraryUseCases(
			GetBandcampCollectionItemsUseCase(api, appContext),
			GetBandcampCollectionSummaryUseCase(api, appContext),
			GetBandcampUserDataUseCase(api, appContext)
		)
	}
	
	@Provides
	@Singleton
	fun provideKtorClient(): HttpClient {
		return HttpClient(Android) {
			install(ContentNegotiation) {
				json(Json)
			}
		}
	}
	
	@Provides
	@Singleton
	fun provideLoginUseCases(
		encRepo: EncRepo
	): LoginUseCases {
		return LoginUseCases(
			SaveBandcampLoginTokenUseCase(encRepo)
		)
	}
	
	@Provides
	@Singleton
	fun providePlayerUseCases(
		api: BandcampApi
	): PlayerUseCases {
		return PlayerUseCases(
			GetBandcampStreamUrl(api)
		)
	}
}