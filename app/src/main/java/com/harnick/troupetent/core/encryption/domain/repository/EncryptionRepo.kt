package com.harnick.troupetent.core.encryption.domain.repository

import java.io.Serializable

interface EncryptionRepo {
	fun <T : Serializable> encryptData(data: T): Pair<ByteArray, ByteArray>
	
	fun <T> decryptData(ivBytes: ByteArray, data: ByteArray): T
}