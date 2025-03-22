package ignacio.guerendiain.uala.core.theme.spec

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    // Dialog
    val dialogBg: Color,
    val dialogButtonOkBg: Color,
    val dialogButtonOkFg: Color,
    val dialogButtonCancelBg: Color,
    val dialogButtonCancelFg: Color,
    val dialogButtonRetryBg: Color,
    val dialogButtonRetryFg: Color,
    val dialogImageBgWarning: Color,
    val dialogImageTintWarning: Color,
    val dialogImageBgFatal: Color,
    val dialogImageTintFatal: Color,

    // Button
    val mainButtonColorBg: Color,
    val mainButtonColorFg: Color,
    val defaultButton: Color,
    val importantButtonBg: Color,
    val importantButtonFg: Color,
    val disabledButtonBg: Color,
    val disabledButtonFg: Color,

    // Common
    val itemSubtitle: Color,
    val defaultScreenBackground: Color,
    val mainTextFieldBg: Color,
    val toolbarTextButton: Color,
    val cardBorder: Color,
    val importantText: Color,
    val defaultText: Color,
    val lightText: Color,
    val textLink: Color,
    val defaultDivider: Color
)