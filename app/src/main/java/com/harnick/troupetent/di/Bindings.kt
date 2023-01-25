package com.harnick.troupetent.di

import com.harnick.troupetent.core.encryption.data.repository.EncryptionRepoImpl
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
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
	abstract fun bindEncRepo(
		encRepoImpl: EncryptionRepoImpl
	): EncryptionRepo
}