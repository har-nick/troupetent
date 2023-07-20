package uk.co.harnick.troupetent.core.localstorage.domain.adapters

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AnyToStringAdapter {
    inline fun <reified T : Any> createAdapter(): ColumnAdapter<T, String> {
        return object : ColumnAdapter<T, String> {
            override fun decode(databaseValue: String): T = Json.decodeFromString(databaseValue)

            override fun encode(value: T): String = Json.encodeToString(value)
        }
    }
}
