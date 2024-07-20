package dev.nonoxy.restaurant_reviews.common.eventbus

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventBusController {
    private val _eventBus = MutableSharedFlow<EventBus>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventBus = _eventBus.asSharedFlow()

    suspend fun publishEvent(event: EventBus) {
        _eventBus.emit(event)
    }
}