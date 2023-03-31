package com.harnick.troupetent.core.util

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.harnick.troupetent.database.PersistentStorage

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(PersistentStorage.Schema, context, "storage.db")
    }
}