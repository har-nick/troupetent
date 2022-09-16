package com.harnick.troupetent.domain.use_cases.collated

import com.harnick.troupetent.domain.use_cases.GetBandcampCollectionItemsUseCase
import com.harnick.troupetent.domain.use_cases.GetBandcampCollectionSummaryUseCase
import com.harnick.troupetent.domain.use_cases.GetBandcampUserDataUseCase

data class LibraryUseCases(
	val getBandcampCollectionItemsUseCase: GetBandcampCollectionItemsUseCase,
	val getBandcampCollectionSummaryUseCase: GetBandcampCollectionSummaryUseCase,
	val getBandcampUserDataUseCase: GetBandcampUserDataUseCase
)