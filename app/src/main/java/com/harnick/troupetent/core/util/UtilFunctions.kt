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

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> fromByteArray(byteArray: ByteArray): T {
	val inputStream = ByteArrayInputStream(byteArray)
	val objectInput = ObjectInputStream(inputStream)
	val result = objectInput.readObject() as T
	
	objectInput.close()
	inputStream.close()
	return result
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

fun closestDivisibleNumber(dividend: Int, divisor: Int): Int {
	return (dividend + divisor) - (dividend % divisor)
}