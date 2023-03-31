package com.harnick.troupetent.di

import com.harnick.troupetent.database.PersistentStorage

expect abstract class DatabaseComponent {
    fun createDb(): PersistentStorage
}
