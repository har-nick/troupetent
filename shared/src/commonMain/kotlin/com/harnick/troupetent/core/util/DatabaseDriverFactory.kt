package com.harnick.troupetent.core.util

import app.cash.sqldelight.db.SqlDriver
import com.harnick.troupetent.database.PersistentStorage
import com.harnick.troupetent.database.Settings_table

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): PersistentStorage {
    val genericColumnAdapter = GenericColumnAdapter()
    val driver = driverFactory.createDriver()

    return PersistentStorage(
        driver,
        Settings_table.Adapter(
            genericColumnAdapter.createAdapter(),
            genericColumnAdapter.createAdapter(),
            genericColumnAdapter.createAdapter(),
            genericColumnAdapter.createAdapter(),
            genericColumnAdapter.createAdapter(),
        )
    )
}