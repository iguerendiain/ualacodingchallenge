package ignacio.guerendiain.uala.core.ui.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.ui.button.MainButton

enum class DialogButtonType { RETRY, CANCEL, OK }

@Composable
fun DialogButton(
    modifier: Modifier = Modifier,
    buttonType: DialogButtonType = DialogButtonType.OK,
    icon: Painter? = null,
    iconResource: Int? = null,
    text: String? = null,
    textResource: Int? = null,
    cb: () -> Unit = {}
){
    val buttonColor: Color
    val fgColor: Color

    when (buttonType){
        DialogButtonType.RETRY -> {
            buttonColor = LocalCurrentColorPalette.current.dialogButtonRetryBg
            fgColor = LocalCurrentColorPalette.current.dialogButtonRetryFg
        }
        DialogButtonType.CANCEL -> {
            buttonColor = LocalCurrentColorPalette.current.dialogButtonCancelBg
            fgColor = LocalCurrentColorPalette.current.dialogButtonCancelFg
        }
        DialogButtonType.OK -> {
            buttonColor = LocalCurrentColorPalette.current.dialogButtonOkBg
            fgColor = LocalCurrentColorPalette.current.dialogButtonOkFg
        }
    }

    MainButton(
        modifier = Modifier.fillMaxWidth().then(modifier),
        buttonColor = buttonColor,
        fgColor = fgColor,
        leftIcon = icon,
        leftIconResource = iconResource,
        text = text,
        textResource = textResource,
        cb = cb
    )
}