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

    // Button
    val defaultButton: Color,
    val disabledButtonBg: Color,
    val disabledButtonFg: Color,

    // Common
    val accentColor: Color,
    val itemSubtitle: Color,
    val defaultScreenBackground: Color,
    val mainTextFieldBg: Color,
    val toolbarTextButton: Color,
    val defaultText: Color,
    val lightText: Color,
    val defaultDivider: Color
)