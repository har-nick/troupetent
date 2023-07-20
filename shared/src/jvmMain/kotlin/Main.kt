import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.context.startKoin
import uk.co.harnick.troupetent.TroupetentApp
import uk.co.harnick.troupetent.core.di.coroutineModule
import uk.co.harnick.troupetent.core.di.platformModule
import uk.co.harnick.troupetent.core.di.sharedModule

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Troupetent",
        state = rememberWindowState(width = 1400.dp, height = 800.dp)
    ) {
        startKoin { modules(coroutineModule, sharedModule, platformModule) }

        TroupetentApp()
    }
}
