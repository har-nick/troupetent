package uk.co.harnick.troupetent.features.library.presentation

import kotlinx.collections.immutable.PersistentList

data class LibraryState(
    val libraryData: PersistentList<Int>? = null
)
