package com.harnick.troupetent.data.repository

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.harnick.troupetent.domain.repository.EncRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class EncRepoImpl @Inject constructor(
	@ApplicationContext private val appContext: Context
) : EncRepo {
	private val mainKey = MasterKey.Builder(appContext)
		.setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
		.build()
	
	override fun encryptData(path: File, data: String) {
		val encryptedFile = EncryptedFile.Builder(
			appContext,
			path,
			mainKey,
			EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
		).build()
		
		if (path.exists()) {
			path.delete()
		}
		
		encryptedFile.openFileOutput().apply {
			write(data.toByteArray(StandardCharsets.UTF_8))
			flush()
			close()
		}
	}
	
	override fun decryptData(path: File): String {
		val encryptedFile = EncryptedFile.Builder(
			appContext,
			path,
			mainKey,
			EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
		).build()
		
		val inputStream = encryptedFile.openFileInput()
		val byteArrayOutputStream = ByteArrayOutputStream()
		var nextByte = inputStream.read()
		
		while (nextByte != -1) {
			byteArrayOutputStream.write(nextByte)
			nextByte = inputStream.read()
		}
		
		return String(byteArrayOutputStream.toByteArray())
	}
}