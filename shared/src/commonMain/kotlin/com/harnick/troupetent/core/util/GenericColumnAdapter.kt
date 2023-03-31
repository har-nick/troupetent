package com.harnick.troupetent.core.util

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: See if this can be generic

class GenericColumnAdapter {
    inline fun <reified T : Any> createAdapter(): ColumnAdapter<T, String> {
        return object : ColumnAdapter<T, String> {
            override fun decode(databaseValue: String): T = Json.decodeFromString(databaseValue)

            override fun encode(value: T): String = Json.encodeToString(value)
        }
    }
}