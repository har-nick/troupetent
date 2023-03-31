package com.harnick.troupetent.di

import com.harnick.troupetent.core.util.Router
import me.tatarka.inject.annotations.Component

@Component
actual abstract class PlatformComponent(@Component protected val sharedComponent: SharedComponent) {
    abstract val router: Router
}
