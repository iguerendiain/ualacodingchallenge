package ignacio.guerendiain.uala.core.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.theme.LocalCurrentTypographyDefs
import ignacio.guerendiain.uala.core.theme.MainTheme

@Composable
fun SearchField(
    value: String,
    textAlign: TextAlign = TextAlign.Start,
    hint: String? = null,
    hintTextAlign: TextAlign = TextAlign.Start,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search,
        keyboardType = KeyboardType.Text
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit = {},
    onKeyboardDone: () -> Unit ={}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchFieldFieldScafolding(
        modifier = modifier,
        startContent = { SearchFieldStartContent() },
        endContent = if (value.isNotBlank()) {
            { SearchFieldEndContent { onValueChange("") } }
        } else
            null,
        textField = {
            Box {
                BasicTextField(
                    value = value,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = KeyboardActions {
                        keyboardController?.hide()
                        onKeyboardDone()
                    },
                    onValueChange = onValueChange,
                    visualTransformation = visualTransformation,
                    textStyle = LocalCurrentTypographyDefs
                        .current
                        .defaultInputValue
                        .copy(
                            color = LocalCurrentColorPalette.current.defaultText,
                            textAlign = textAlign
                        ),
                    modifier = Modifier.fillMaxWidth()
                )

                if (hint != null && value.isBlank()) Text(
                    text = hint,
                    style = LocalCurrentTypographyDefs
                        .current
                        .defaultInputValue
                        .copy(
                            color = LocalCurrentColorPalette.current.defaultText.copy(alpha = .5f),
                            textAlign = hintTextAlign
                        ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Composable
fun SearchFieldStartContent(){
    Image(
        painter = painterResource(R.drawable.ic_search),
        contentDescription = null,
        colorFilter = ColorFilter.tint(LocalCurrentColorPalette.current.lightText),
        modifier = Modifier.size(16.dp)
    )
}


@Composable
fun SearchFieldEndContent(cb: () -> Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(18.dp)
            .background(
                color = LocalCurrentColorPalette.current.lightText,
                shape = CircleShape
            )
            .clickable { cb() }
            .padding(2.dp)
    ){
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = null
        )
    }
}

@Composable
fun SearchFieldFieldScafolding(
    modifier: Modifier = Modifier,
    startContent: @Composable (() -> Unit)? = null,
    textField: @Composable () -> Unit,
    endContent: @Composable (() -> Unit)? = null
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(36.dp)
            .background(
                shape = RoundedCornerShape(50),
                color = LocalCurrentColorPalette.current.mainTextFieldBg
            )
            .padding(horizontal = 8.dp)
    ) {
        startContent?.let {
            it()
            Spacer(Modifier.width(8.dp))
        }

        Box(Modifier.weight(1f)){ textField() }

        endContent?.let {
            Spacer(Modifier.width(8.dp))
            it()
        }
    }
}

@Composable
@Preview
fun SearchFieldPreview(){
    MainTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .background(LocalCurrentColorPalette.current.defaultScreenBackground)
                .padding(12.dp)
        ){
            SearchField(
                value = "A ver como queda",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            SearchField(
                value = "",
                hint = "Hola que tal...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
        }
    }
}