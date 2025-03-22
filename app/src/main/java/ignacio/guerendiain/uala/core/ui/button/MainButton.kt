package ignacio.guerendiain.uala.core.ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.theme.LocalCurrentTypographyDefs

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    buttonColor: Color? = null,
    borderColor: Color? = null,
    leftContent: (@Composable ()-> Unit)? = null,
    centerContent: (@Composable ()-> Unit)? = null,
    rightContent: (@Composable ()-> Unit)? = null,
    disabledButtonColor: Color? = null,
    disabledBorderColor: Color? = null,
    enabled: Boolean = true,
    cb: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .then(
                Modifier
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(50)
                    )
                    .background(
                        shape = RoundedCornerShape(50),
                        color = if (enabled)
                            buttonColor?: LocalCurrentColorPalette.current.defaultButton
                        else
                            disabledButtonColor?: LocalCurrentColorPalette.current.disabledButtonBg
                    )
                    .then(
                        (if (enabled) borderColor else disabledBorderColor)?.let {
                            Modifier.border(
                                width = 1.dp,
                                color = it,
                                shape = RoundedCornerShape(50)
                            )
                        }?:Modifier
                    )
                    .then(
                        if (enabled) Modifier.clickable { cb() }
                        else Modifier
                    )
                    .padding(horizontal = 10.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = contentModifier.height(44.dp)
        ) {
            leftContent?.let {
                it()
                Spacer(Modifier.width(7.dp))
            }

            Box(modifier = Modifier.weight(1f)){
                centerContent?.invoke()
            }

            rightContent?.let {
                Spacer(Modifier.width(7.dp))
                it()
            }
        }
    }
}

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    buttonColor: Color? = null,
    fgColor: Color? = null,
    borderColor: Color? = null,
    leftIcon: Painter? = null,
    leftIconResource: Int? = null,
    rightIcon: Painter? = null,
    rightIconResource: Int? = null,
    text: String? = null,
    textResource: Int? = null,
    disabledButtonColor: Color? = null,
    disabledFgColor: Color? = null,
    disabledBorderColor: Color? = null,
    enabled: Boolean = true,
    cb: () -> Unit = {}
){
    val leftContent = @Composable {
        MainButtonIcon(
            iconRes = leftIconResource,
            icon = leftIcon,
            tint = fgColor,
        )
    }

    val centerContent = @Composable {
        MainButtonText(
            textResource = textResource,
            text = text,
            enabled = enabled,
            color = fgColor,
            disabledColor = disabledFgColor
        )
    }

    val rightContent = @Composable {
        MainButtonIcon(
            iconRes = rightIconResource,
            icon = rightIcon,
            tint = fgColor,
        )
    }

    MainButton(
        modifier = modifier,
        contentModifier = contentModifier,
        buttonColor = buttonColor,
        borderColor = borderColor,
        leftContent = leftContent,
        centerContent = centerContent,
        rightContent = rightContent,
        disabledButtonColor = disabledButtonColor,
        disabledBorderColor = disabledBorderColor,
        enabled = enabled,
        cb = cb
    )
}

@Composable
fun MainButtonIcon(
    iconRes: Int? = null,
    icon: Painter? = null,
    tint: Color? = null
){
    icon
        ?:iconRes
            ?.let { painterResource(it) }
            ?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    colorFilter = tint?.let { t -> ColorFilter.tint(t) },
                    modifier = Modifier.width(20.dp)
                )
            }
}

@Composable
fun MainButtonText(
    text: String? = null,
    textResource: Int? = null,
    enabled: Boolean = true,
    color: Color? = null,
    disabledColor: Color? = null
){
    (text?:textResource?.let { stringResource(it) })?.let {
        Text(
            text = it,
            style = LocalCurrentTypographyDefs.current.buttonText,
            color = if (enabled) color ?: LocalCurrentColorPalette.current.defaultText
            else disabledColor ?: LocalCurrentColorPalette.current.disabledButtonFg,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(1f)
        )
    }
}