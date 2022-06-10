package com.harnick.troupetent.util

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class CryptoUtils {

  private lateinit var keySpec: KeyGenParameterSpec

  init {
	genKeySpec()
  }

  fun genKeySpec() {
	// Creates a more secure key spec if supported with fallback for API < 21

	keySpec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
	  KeyGenParameterSpec.Builder(
		"enckey",
		KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
	  ).apply {
		setBlockModes(KeyProperties.BLOCK_MODE_GCM)
		setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
		setKeySize(256)

		// TODO: ADD BIOMETRIC SUPPORT
//		if (SharedPreferenceUtils.read("requireUserAuth", false) as Boolean) {
//		  setUserAuthenticationRequired(true)
//		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
		  setUnlockedDeviceRequired(true)
		}
	  }.build()
	} else {
	  MasterKeys.AES256_GCM_SPEC
	}
  }

  private val keyAlias = MasterKeys.getOrCreate(keySpec)

  fun writeToEncryptedFile(context: Context, fileName: String, fileContent: String) {

	if (File(context.filesDir, fileName).isFile) {
	  File(context.filesDir, fileName).delete()
	}

	val encryptedFile = EncryptedFile.Builder(
	  File(context.filesDir, fileName),
	  context,
	  keyAlias,
	  EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
	).build()

	val contents = fileContent.toByteArray(StandardCharsets.UTF_8)

	encryptedFile.openFileOutput().apply {
	  write(contents)
	  flush()
	  close()
	}
  }

  fun decryptFile(context: Context, fileName: String): String {
	lateinit var plainText: String
	val encryptedFile = File(context.filesDir, fileName)

	if (encryptedFile.isFile) { // isFile avoids redundant isDirectory check
	  val blankFile = EncryptedFile.Builder(
		encryptedFile,
		context,
		keyAlias,
		EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
	  ).build()

	  val inputStream = blankFile.openFileInput()
	  val byteArrayOutputStream = ByteArrayOutputStream()
	  var nextByte: Int = inputStream.read()

	  while (nextByte != -1) {
		byteArrayOutputStream.write(nextByte)
		nextByte = inputStream.read()
	  }

	  plainText = String(byteArrayOutputStream.toByteArray())
	}
	return plainText
  }
}