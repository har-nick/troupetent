package com.harnick.troupetent.core.resource_provider.domain.repository

import androidx.annotation.StringRes

interface ResourceProvider {
	fun getString(@StringRes id: Int): String
}