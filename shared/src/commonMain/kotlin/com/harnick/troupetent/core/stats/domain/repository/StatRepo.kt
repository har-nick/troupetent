package com.harnick.troupetent.core.stats.domain.repository

import kotlinx.coroutines.flow.Flow

interface StatRepo {
    fun getLaunchCount(): Flow<Long?>

    fun iterateLaunchCount()
}