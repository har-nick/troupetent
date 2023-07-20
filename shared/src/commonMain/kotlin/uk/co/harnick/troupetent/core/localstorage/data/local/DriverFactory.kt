package uk.co.harnick.troupetent.core.localstorage.data.local

import app.cash.sqldelight.db.SqlDriver
import localstorage.LibraryItemEntity
import uk.co.harnick.troupetent.LocalStorage
import uk.co.harnick.troupetent.core.localstorage.domain.adapters.AnyToStringAdapter

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createLocalStorage(driverFactory: DriverFactory): LocalStorage {
    val driver = driverFactory.createDriver()
    return LocalStorage(
        driver,
        LibraryItemEntity.Adapter(
            typeAdapter = AnyToStringAdapter.createAdapter(),
            track_listAdapter = AnyToStringAdapter.createAdapter(),
            favorite_trackAdapter = AnyToStringAdapter.createAdapter()
        )
    )
}
