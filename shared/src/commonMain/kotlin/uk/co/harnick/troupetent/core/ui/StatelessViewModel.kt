package uk.co.harnick.troupetent.core.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope

abstract class StatelessViewModel : ScreenModel {
    val vmScope = coroutineScope
}
