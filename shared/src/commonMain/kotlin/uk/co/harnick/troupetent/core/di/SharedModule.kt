package uk.co.harnick.troupetent.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import uk.co.harnick.troupetent.core.account.data.repository.AccountRepoImpl
import uk.co.harnick.troupetent.core.account.domain.repository.AccountRepo
import uk.co.harnick.troupetent.core.account.presentation.AccountViewModel
import uk.co.harnick.troupetent.features.library.data.repository.LibraryRepoImpl
import uk.co.harnick.troupetent.features.library.domain.repository.LibraryRepo
import uk.co.harnick.troupetent.features.onboarding.presentation.OnboardingViewModel

val sharedModule = module {
    single { HttpClient(CIO) }
    singleOf(::AccountRepoImpl) { bind<AccountRepo>() }
    singleOf(::LibraryRepoImpl) { bind<LibraryRepo>() }

    factoryOf(::AccountViewModel)
    singleOf(::OnboardingViewModel)
}
