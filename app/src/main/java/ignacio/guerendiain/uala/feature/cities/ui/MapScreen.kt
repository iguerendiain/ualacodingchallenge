package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import ignacio.guerendiain.uala.core.ui.util.isPortrait
import ignacio.guerendiain.uala.feature.cities.vm.MapViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
    cityId: Long,
    mapViewModel: MapViewModel = koinViewModel()
){
    val mapState by mapViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(cityId) {
        mapViewModel.loadCity(cityId)
    }

    MapContent(
        city = mapState.city,
        isPortrait = isPortrait(),
        onBack = { navController.navigateUp() }
    )
}