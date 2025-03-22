package ignacio.guerendiain.uala.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import ignacio.guerendiain.uala.core.theme.defs.DarkColorPalette
import ignacio.guerendiain.uala.core.theme.defs.DefaultTypographyDefs
import ignacio.guerendiain.uala.core.theme.defs.LightColorPalette

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorPalette = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalCurrentColorPalette provides colorPalette,
        LocalCurrentTypographyDefs provides DefaultTypographyDefs
    ) {
        content()
    }
}

val LocalCurrentColorPalette = staticCompositionLocalOf { LightColorPalette }
val LocalCurrentTypographyDefs = staticCompositionLocalOf { DefaultTypographyDefs }
