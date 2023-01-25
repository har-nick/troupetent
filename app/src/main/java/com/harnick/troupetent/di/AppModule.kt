package com.harnick.troupetent.di

import android.content.Context
import androidx.room.Room
import coil.ImageLoader
import com.harnick.troupetent.core.app_settings.data.model.room.SettingsDatabase
import com.harnick.troupetent.core.app_settings.data.repository.SettingsRepoImpl
import com.harnick.troupetent.core.app_settings.domain.repository.SettingsRepo
import com.harnick.troupetent.core.user_data.data.model.room.bandcamp.BandcampUserDataDatabase
import com.harnick.troupetent.core.user_data.data.repository.UserDataRepoImpl
import com.harnick.troupetent.core.user_data.domain.repository.UserDataRepo
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
	fun provideKtorClient(): HttpClient {
		return HttpClient(Android) {
			install(ContentNegotiation) {
				json(Json)
			}
		}
	}
	
	@Provides
	@Singleton
	fun provideSettingsDatabase(
		@ApplicationContext appContext: Context
	): SettingsDatabase {
		return Room.databaseBuilder(
			appContext,
			SettingsDatabase::class.java,
			SettingsDatabase.DB_NAME
		).build()
	}
	
	@Provides
	@Singleton
	fun provideSettingsRepo(database: SettingsDatabase): SettingsRepo {
		return SettingsRepoImpl(database.settingsDao)
	}
	
	@Provides
	@Singleton
	fun provideUserDataDatabase(
		@ApplicationContext appContext: Context
	): BandcampUserDataDatabase {
		return Room.databaseBuilder(
			appContext,
			BandcampUserDataDatabase::class.java,
			BandcampUserDataDatabase.DB_NAME
		).build()
	}
	
	@Provides
	@Singleton
	fun provideUserDataRepo(database: BandcampUserDataDatabase): UserDataRepo {
		return UserDataRepoImpl(database.bandcampUserDataDao)
	}
}