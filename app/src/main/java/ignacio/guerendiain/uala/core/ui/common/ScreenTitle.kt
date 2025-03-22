package ignacio.guerendiain.uala.core.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.theme.LocalCurrentTypographyDefs

@Composable
fun ScreenTitle(title: String, modifier: Modifier = Modifier){
    Text(
        text = title,
        color = LocalCurrentColorPalette.current.defaultText,
        style = LocalCurrentTypographyDefs.current.defaultScreenTitle,
        modifier = modifier
    )
}

@Composable
fun ScreenTitle(titleRes: Int, modifier: Modifier = Modifier){
    ScreenTitle(stringResource(titleRes), modifier)
}