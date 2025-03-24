package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.ui.button.BackButton
import ignacio.guerendiain.uala.core.ui.button.ToolbarIconButton
import ignacio.guerendiain.uala.core.ui.common.Toolbar
import ignacio.guerendiain.uala.core.ui.util.StatusbarSpacer

@Composable
fun MapContent(
    city: City?,
    isPortrait: Boolean,
    onBack: () -> Unit
){
    val cameraPosition = rememberCameraPositionState()
    val context = LocalContext.current

    val lightMapTheme = MapStyleOptions.loadRawResourceStyle(context, R.raw.googlemaps_light_theme)
    val darkMapTheme = MapStyleOptions.loadRawResourceStyle(context, R.raw.googlemaps_dark_theme)

    val cityLocation = if (city?.lat != null && city.lon != null)
        LatLng(city.lat, city.lon)
    else null

    if (cityLocation!=null)
        cameraPosition.position = CameraPosition.fromLatLngZoom(cityLocation, 12f)

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ){
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPosition,
                properties = MapProperties(
                    mapStyleOptions = if (isSystemInDarkTheme()) darkMapTheme else lightMapTheme
                ),
                contentPadding = with(LocalDensity.current) {
                    PaddingValues(
                        top = WindowInsets.statusBars.getTop(this).toDp(),
                        bottom = WindowInsets.navigationBars.getBottom(this).toDp()
                    )
                }
            ){
                if (cityLocation!=null) Marker(
                    state = MarkerState(position = cityLocation),
                    title = "${city?.name} [${city?.country}]",
                    snippet = "${cityLocation.latitude}, ${cityLocation.longitude}"
                )
            }
        }

        if (isPortrait){
            Column(modifier = Modifier.fillMaxWidth()
            ) {
                StatusbarSpacer()
                Toolbar(
                    startContent = {
                        BackButton(modifier = Modifier.clickable { onBack() })
                    },
                    centerContent = {},
                    endContent = {}
                )
            }
        }
    }
}