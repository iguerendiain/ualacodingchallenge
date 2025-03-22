package ignacio.guerendiain.uala.core.ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette

@Composable
fun ToolbarIconButton(
    painter: Painter? = null,
    res: Int? = null,
    contentDescription: String? = null,
    contentDescriptionRes: Int? = null,
    modifier: Modifier = Modifier
) {
    val solvedContentDescription =
        contentDescription ?: contentDescriptionRes?.let { stringResource(it) }

    painter ?: res?.let { painterResource(it) }?.let { icon ->
        Image(
            painter = icon,
            colorFilter = ColorFilter.tint(LocalCurrentColorPalette.current.defaultText),
            contentDescription = solvedContentDescription,
            modifier = Modifier
                .size(36.dp)
                .padding(6.dp)
                .then(modifier)
        )
    }
}