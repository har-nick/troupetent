package com.harnick.troupetent

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.harnick.troupetent.di.SharedComponent
import com.harnick.troupetent.di.DatabaseComponent
import com.harnick.troupetent.di.PlatformComponent
import com.harnick.troupetent.di.create
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databaseComponent = DatabaseComponent::class.create(application)
        val sharedComponent = SharedComponent::class.create(databaseComponent)
        val platformComponent = PlatformComponent::class.create(sharedComponent)

        GlobalScope.launch {
            platformComponent.router
        }
    }
}
