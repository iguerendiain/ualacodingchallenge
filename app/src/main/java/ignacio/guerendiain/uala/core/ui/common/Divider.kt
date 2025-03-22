package ignacio.guerendiain.uala.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette

@Composable
fun Divider(modifier: Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(LocalCurrentColorPalette.current.defaultDivider)
            .then(modifier)
    )
}