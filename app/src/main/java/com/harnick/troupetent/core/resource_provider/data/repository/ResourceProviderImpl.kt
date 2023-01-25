package com.harnick.troupetent.core.resource_provider.data.repository

import android.content.Context
import com.harnick.troupetent.core.resource_provider.domain.repository.ResourceProvider

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
	override fun getString(id: Int): String =	context.getString(id)
}