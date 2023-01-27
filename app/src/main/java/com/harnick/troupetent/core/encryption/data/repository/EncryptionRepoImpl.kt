package com.harnick.troupetent.core.encryption.data.repository

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.util.fromByteArray
import com.harnick.troupetent.core.util.toByteArray
import java.io.Serializable
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class EncryptionRepoImpl @Inject constructor() : EncryptionRepo {
	
	private companion object {
		const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
		const val BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
		const val PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
		const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
	}
	
	private val keystore = KeyStore.getInstance("AndroidKeyStore")
	private val keyGenerator = KeyGenerator.getInstance(ALGORITHM, "AndroidKeyStore")
	private val keyGenParameterSpec = KeyGenParameterSpec
		.Builder(
			"TroupetentKeyAlias",
			KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
		)
		.setBlockModes(BLOCK_MODE)
		.setEncryptionPaddings(PADDING)
		.setRandomizedEncryptionRequired(true)
		.build()
	
	
	private val cipher = Cipher.getInstance(TRANSFORMATION)
	
	init {
		keystore.load(null)
		
		if (!keystore.containsAlias("TroupetentKeyAlias")) {
			keyGenerator.init(keyGenParameterSpec)
			keyGenerator.generateKey()
		}
	}
	
	private fun getKey(): SecretKey {
		val keyEntry = keystore.getEntry("TroupetentKeyAlias", null) as KeyStore.SecretKeyEntry
		return keyEntry.secretKey
	}
	
	override fun <T : Serializable> encryptData(data: T): Pair<ByteArray, ByteArray> {
		val dataAsArray = data.toByteArray()
		
		cipher.init(Cipher.ENCRYPT_MODE, getKey())
		
		val ivBytes = cipher.iv
		val encryptedData = cipher.doFinal(dataAsArray)
		
		return Pair(ivBytes, encryptedData)
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T> decryptData(ivBytes: ByteArray, data: ByteArray): T {
		val ivSpec = GCMParameterSpec(128, ivBytes)
		
		cipher.init(Cipher.DECRYPT_MODE, getKey(), ivSpec)
		
		val decryptedArray: ByteArray = cipher.doFinal(data)
		return fromByteArray(decryptedArray) as T
	}
}