package ignacio.guerendiain.uala.core.ui.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun isPortrait(): Boolean {
    return LocalContext
        .current
        .resources
        .configuration
        .orientation == Configuration.ORIENTATION_PORTRAIT
}