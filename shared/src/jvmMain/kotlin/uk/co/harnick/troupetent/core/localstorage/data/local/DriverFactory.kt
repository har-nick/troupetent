package uk.co.harnick.troupetent.core.localstorage.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import uk.co.harnick.troupetent.LocalStorage

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        LocalStorage.Schema.create(driver)
        return driver
    }
}
