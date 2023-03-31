package com.harnick.troupetent.core.stats.data.repository

import app.cash.sqldelight.coroutines.asFlow
import com.harnick.troupetent.core.stats.domain.repository.StatRepo
import com.harnick.troupetent.database.PersistentStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@Inject
class StatRepoImpl(private val db: PersistentStorage) : StatRepo {
    private val queries = db.stat_tableQueries

    override fun getLaunchCount(): Flow<Long?> {
        return queries.getLaunchCount()
            .asFlow()
            .map { it.executeAsOneOrNull() }
            .flowOn(Dispatchers.IO)
    }

    override fun iterateLaunchCount() = queries.iterateLaunchCount()
}