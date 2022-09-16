package com.harnick.troupetent.presentation.screens.library.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.time.LocalDateTime

private var greetings = mutableListOf(
	"Hello",
	"Hi",
	"Hey"
)

private fun generateGreeting(): String {
	val currentTime = LocalDateTime.now().hour

	if ((5..11).contains(currentTime)) {
		greetings.add("Good morning")
	} else if ((12..16).contains(currentTime)) {
		greetings.add("Good afternoon")
	} else if ((17..20).contains(currentTime)) {
		greetings.add("Good evening")
	} else {
		greetings.add("Good night")
	}

	return greetings.random()
}

@Composable
fun HeaderGreeting(
	username: String
) {
	val greeting = remember { "${generateGreeting()}\n$username!" }
	Text(
		greeting,
		Modifier,
		MaterialTheme.colorScheme.onPrimaryContainer,
		style = MaterialTheme.typography.titleMedium
	)
}