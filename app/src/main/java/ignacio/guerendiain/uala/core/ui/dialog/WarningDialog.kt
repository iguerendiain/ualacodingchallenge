package ignacio.guerendiain.uala.core.ui.dialog

import androidx.compose.runtime.Composable
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette

@Composable
fun WarningDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    titleRes: Int? = null,
    description: String? = null,
    descriptionRes: Int? = null,
    retryButtonText: String? = null,
    retryButtonRes: Int? = null,
    retryButtonCb: (() -> Unit)? = null,
    cancelButtonText: String? = null,
    cancelButtonRes: Int? = null,
    cancelButtonCb: (() -> Unit)? = null,
){
    val buttons = mutableListOf<@Composable ()->Unit>().apply {
        if (retryButtonCb != null)add @Composable {
            DialogButton(
                buttonType = DialogButtonType.RETRY,
                text = retryButtonText,
                textResource = retryButtonRes,
                cb = retryButtonCb
            )
        }

        if (cancelButtonCb != null) add @Composable {
            DialogButton(
                buttonType = DialogButtonType.CANCEL,
                text = cancelButtonText,
                textResource = cancelButtonRes,
                cb = cancelButtonCb
            )
        }
    }.toTypedArray()

    MainDialog(
        onDismissRequest = onDismissRequest,
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true,
        imageRes = R.drawable.ic_exclamation,
        imageTint = LocalCurrentColorPalette.current.dialogImageTintWarning,
        imageBgColor = LocalCurrentColorPalette.current.dialogImageBgWarning,
        title = title,
        titleRes = titleRes,
        description = description,
        descriptionRes = descriptionRes,
        buttons = buttons
    )
}
