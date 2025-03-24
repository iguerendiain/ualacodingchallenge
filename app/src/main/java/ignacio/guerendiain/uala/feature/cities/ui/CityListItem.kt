package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.theme.LocalCurrentTypographyDefs

@Composable
fun CityListItem(
    name: String,
    country: String,
    isFavorite: Boolean,
    lat: Double? = null,
    lon: Double? = null,
    modifier: Modifier = Modifier,

    onFavoriteToggle: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
    ){
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = "%s [%s]".format(name, country),
                style = LocalCurrentTypographyDefs.current.itemTitle.copy(
                    color = LocalCurrentColorPalette.current.defaultText
                )
            )
            if (lat!=null && lon!=null){
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "%f, %f".format(lat, lon),
                    style = LocalCurrentTypographyDefs.current.itemSubtitle.copy(
                        color = LocalCurrentColorPalette.current.itemSubtitle
                    )
                )
            }
        }
        Spacer(Modifier.size(12.dp))
        Image(
            modifier = Modifier.size(24.dp).clickable { onFavoriteToggle() },
            painter = painterResource(if (isFavorite)
                R.drawable.ic_favorite_on
            else
                R.drawable.ic_favorite_off
            ),
            colorFilter = ColorFilter.tint(LocalCurrentColorPalette.current.accentColor),
            contentDescription = null
        )
    }
}