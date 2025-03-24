package ignacio.guerendiain.uala.core.theme.defs

import androidx.compose.ui.graphics.Color
import ignacio.guerendiain.uala.core.theme.db.ColorDB
import ignacio.guerendiain.uala.core.theme.spec.ColorPalette

val DarkColorPalette = ColorPalette(
    // Dialog
    dialogBg = ColorDB.GrayDark,
    dialogButtonOkBg = ColorDB.CyanDark,
    dialogButtonOkFg = ColorDB.White,
    dialogButtonCancelBg = ColorDB.RedMainDark,
    dialogButtonCancelFg = ColorDB.White,
    dialogButtonRetryBg = ColorDB.GrayMain,
    dialogButtonRetryFg = ColorDB.GrayLight1,
    dialogImageBgWarning = ColorDB.YellowMain,
    dialogImageTintWarning = ColorDB.White,

    // Button
    defaultButton = ColorDB.White,
    disabledButtonBg = ColorDB.GrayLight1,
    disabledButtonFg = ColorDB.GrayMain,
    accentColor = ColorDB.CyanDark,

    // Common
    defaultScreenBackground = Color.Black,
    mainTextFieldBg = ColorDB.GrayDark,
    toolbarTextButton = ColorDB.BlueMain,
    itemSubtitle = ColorDB.GrayLight1,
    defaultText = ColorDB.White,
    lightText = ColorDB.GrayLight1,
    defaultDivider = ColorDB.GrayDark,
)