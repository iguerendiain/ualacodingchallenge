package ignacio.guerendiain.uala.core.theme.defs

import androidx.compose.ui.graphics.Color
import ignacio.guerendiain.uala.core.theme.db.ColorDB
import ignacio.guerendiain.uala.core.theme.spec.ColorPalette

val LightColorPalette = ColorPalette(
    // Dialog
    dialogBg = ColorDB.White,
    dialogButtonOkBg = ColorDB.CyanMain,
    dialogButtonOkFg = ColorDB.White,
    dialogButtonCancelBg = ColorDB.RedLight1,
    dialogButtonCancelFg = ColorDB.White,
    dialogButtonRetryBg = ColorDB.White,
    dialogButtonRetryFg = ColorDB.GrayMain,
    dialogImageBgWarning = ColorDB.YellowMain,
    dialogImageTintWarning = ColorDB.White,

    // Button
    defaultButton = ColorDB.White,
    disabledButtonBg = ColorDB.GrayLight1,
    disabledButtonFg = ColorDB.GrayMain,
    accentColor = ColorDB.CyanMain,

    // Common
    defaultScreenBackground = ColorDB.White,
    mainTextFieldBg = ColorDB.GrayLight3,
    toolbarTextButton = ColorDB.BlueMain,
    itemSubtitle = ColorDB.GrayLight1,
    defaultText = ColorDB.GrayMain,
    lightText = ColorDB.GrayLight1,
    defaultDivider = ColorDB.GrayLight1,
)