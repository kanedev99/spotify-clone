package foit.thotrinh.spotifyclone.exoplayer


class FirebaseMusicSource {
    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()

    private var state = State.CREATED
        set(value) {
            if (value == State.INITIALED || value == State.ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(value == State.INITIALED)
                    }
                }
            } else {
                field = value
            }
        }

    fun whenReady(action: (Boolean) -> Unit): Boolean {
        return if (state == State.CREATED || state == State.INITIALIZING) {
            onReadyListeners += action
            false
        } else {
            action(state == State.INITIALED)
            true
        }
    }
}

enum class State {
    CREATED,
    INITIALIZING,
    INITIALED,
    ERROR
}