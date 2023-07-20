package uk.co.harnick.troupetent.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

typealias MainDispatcher = CoroutineDispatcher
typealias DefaultDispatcher = CoroutineDispatcher
typealias IODispatcher = CoroutineDispatcher

val coroutineModule = module {
    singleOf<MainDispatcher>(Dispatchers::Main)
    singleOf<DefaultDispatcher>(Dispatchers::Default)
    singleOf<IODispatcher>(Dispatchers::IO)
}
