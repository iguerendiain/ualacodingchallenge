package ignacio.guerendiain.uala.core.ui.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.theme.LocalCurrentTypographyDefs

@Composable
fun ToolbarTextButton(
    text: String? = null,
    res: Int? = null,
    modifier: Modifier = Modifier
) {
    text?: res?.let { stringResource(it) }?.let {
        Text(
            text = it,
            style = LocalCurrentTypographyDefs.current.toolbarButton.copy(
                color = LocalCurrentColorPalette.current.toolbarTextButton
            ),
            modifier = Modifier
                .padding(6.dp)
                .then(modifier)
        )
    }
}