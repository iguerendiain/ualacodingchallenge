package ignacio.guerendiain.uala.core.ui.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ignacio.guerendiain.uala.R

@Composable
fun BackButton(
    contentDescription: String? = null,
    contentDescriptionRes: Int? = null,
    modifier: Modifier = Modifier
){
    ToolbarIconButton(
        res = R.drawable.ic_back,
        contentDescription = contentDescription,
        contentDescriptionRes = contentDescriptionRes,
        modifier = modifier
    )
}