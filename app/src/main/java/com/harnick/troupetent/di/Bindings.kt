package com.harnick.troupetent.di

import com.harnick.troupetent.data.repository.DataStoreRepoImpl
import com.harnick.troupetent.data.repository.EncRepoImpl
import com.harnick.troupetent.domain.repository.DataStoreRepo
import com.harnick.troupetent.domain.repository.EncRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Bindings {
	@Binds
	@Singleton
	abstract fun bindDataStoreRepo(
		dataStoreRepoImpl: DataStoreRepoImpl
	): DataStoreRepo

	@Binds
	@Singleton
	abstract fun bindEncRepo(
		encRepoImpl: EncRepoImpl
	): EncRepo
}