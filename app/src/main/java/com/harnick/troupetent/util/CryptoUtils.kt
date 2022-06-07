package com.harnick.troupetent.util

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File
import java.nio.charset.StandardCharsets

class CryptoUtils {
  
  // Creates a more secure key spec if supported with fallback for API < 21
  
  private val spec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
	KeyGenParameterSpec.Builder(
	  "enckey",
	  KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
	).apply {
	  setBlockModes(KeyProperties.BLOCK_MODE_GCM)
	  setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
	  setKeySize(256)
	  
	  if (SharedPreferenceUtils.read("requireUserAuth", true) as Boolean) {
		setUserAuthenticationRequired(true)
	  }
	  
	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
		setUnlockedDeviceRequired(true)
	  }
	}.build()
  } else {
	MasterKeys.AES256_GCM_SPEC
  }
  
  private val keyAlias = MasterKeys.getOrCreate(spec)
  
  fun encryptFile(context: Context, fileName: String, fileContent: String) {
	val blankFile = EncryptedFile.Builder(
	  File(context.filesDir, fileName),
	  context,
	  keyAlias,
	  EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
	).build()
	
	val contents = fileContent.toByteArray(StandardCharsets.UTF_8)
	
	blankFile.openFileOutput().apply {
	  write(contents)
	  flush()
	  close()
	}
  }
  
  fun decryptFile(fileName: String) {
	
  }
}