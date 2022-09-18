package com.harnick.troupetent.domain.repository

import java.io.File

interface EncRepo {
	fun encryptData(path: File, data: String)
	
	fun decryptData(path: File): String
}