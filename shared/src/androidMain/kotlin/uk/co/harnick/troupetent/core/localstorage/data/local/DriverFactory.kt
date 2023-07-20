package uk.co.harnick.troupetent.core.localstorage.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import uk.co.harnick.troupetent.LocalStorage

actual class DriverFactory(private val appContext: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(LocalStorage.Schema, appContext, "localstorage.db")
    }
}
