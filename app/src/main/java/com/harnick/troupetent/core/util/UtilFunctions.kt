package com.harnick.troupetent.core.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.io.*

fun Context.findActivity(): Activity? = when (this) {
	is Activity -> this
	is ContextWrapper -> baseContext.findActivity()
	else -> null
}

fun Serializable.toByteArray(): ByteArray {
	val outputStream = ByteArrayOutputStream()
	val objectOutput = ObjectOutputStream(outputStream)
	
	objectOutput.writeObject(this)
	objectOutput.flush()
	
	val result = outputStream.toByteArray()
	
	outputStream.close()
	objectOutput.close()
	
	return result
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> fromByteArray(data: ByteArray): T {
	val inputStream = ByteArrayInputStream(data)
	val objectInput = ObjectInputStream(inputStream)
	val result = objectInput.readObject() as T
	
	objectInput.close()
	inputStream.close()
	return result
}