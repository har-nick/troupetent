package uk.co.harnick.troupetent.core.ui

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class StatefulViewModel<State, Event>(
    private val initialState: State
) : StateScreenModel<State>(initialState) {
    // More descriptive
    protected val vmScope = coroutineScope

    private val _uiEvent = Channel<Event>()
    val uiEvent = _uiEvent.consumeAsFlow()

    protected fun sendEvent(event: Event) {
        vmScope.launch { _uiEvent.send(event) }
    }

    abstract fun onEvent(event: Event)
}
