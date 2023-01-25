package com.harnick.troupetent.core.encryption.data.repository

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import com.harnick.troupetent.core.encryption.domain.repository.EncryptionRepo
import com.harnick.troupetent.core.util.closestDivisibleNumber
import com.harnick.troupetent.core.util.fromByteArray
import com.harnick.troupetent.core.util.toByteArray
import java.io.Serializable
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.inject.Inject

class EncryptionRepoImpl @Inject constructor() : EncryptionRepo {
	
	private val keyGenerator =
		KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
	private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
		"TroupetentKeyAlias",
		KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
	)
		.setBlockModes(KeyProperties.BLOCK_MODE_CBC)
		.setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
		.build()
	
	private val keystore = KeyStore.getInstance("AndroidKeyStore")
	
	private val cipher = Cipher.getInstance("AES/CBC/NoPadding")
	
	
	init {
		keyGenerator.init(keyGenParameterSpec)
		keyGenerator.generateKey()
		keystore.load(null)
	}
	
	private fun getKey(): SecretKey {
		val keyEntry = keystore.getEntry("TroupetentKeyAlias", null) as KeyStore.SecretKeyEntry
		return keyEntry.secretKey
	}
	
	override fun <T : Serializable> encryptData(data: T): Pair<ByteArray, ByteArray> {
		var temp = data.toByteArray()
		
		if (temp.size % 16 != 0) {
			temp = temp.copyOf(
				closestDivisibleNumber(temp.size, 16)
			)
		}
		
		cipher.init(Cipher.ENCRYPT_MODE, getKey())
		
		val ivBytes = cipher.iv
		val encryptedArray = cipher.doFinal(temp)
		
		return Pair(ivBytes, encryptedArray)
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T> decryptData(ivBytes: ByteArray, data: ByteArray): T {
		val ivSpec = IvParameterSpec(ivBytes)
		
		cipher.init(Cipher.DECRYPT_MODE, getKey(), ivSpec)
		val tempArray: ByteArray = cipher.doFinal(data)
		return fromByteArray(tempArray) as T
	}
}