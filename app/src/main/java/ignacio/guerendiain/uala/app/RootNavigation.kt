package ignacio.guerendiain.uala.app

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.theme.LocalCurrentColorPalette
import ignacio.guerendiain.uala.core.ui.dialog.APIResultErrorDialog
import ignacio.guerendiain.uala.core.util.LoadingStatus
import ignacio.guerendiain.uala.feature.cities.MapCityNavigation
import org.koin.androidx.compose.koinViewModel

object RootNavArguments{
    const val ARGS_CITYID = "cityId"
}

enum class RootNavDestination(
    val arguments: List<NamedNavArgument> = listOf()
){
    CITYLISTSCREEN,
    MAPSCREEN(
        arguments = listOf(
            navArgument(RootNavArguments.ARGS_CITYID)
            {
                type = NavType.LongType
                nullable = false
                defaultValue = -1
            }
        )
    )
}

@Composable
fun RootNavigation() {
    val activity = LocalActivity.current
    val navController = rememberNavController()
    val mainViewModel = koinViewModel<MainViewModel>()
    val mainState by mainViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { mainViewModel.downloadCities() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(LocalCurrentColorPalette.current.defaultScreenBackground)
    ) {
        when (mainState.loadingResult.status) {
            LoadingStatus.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(70.dp),
                    color = LocalCurrentColorPalette.current.lightText
                )
            }
            LoadingStatus.SUCCESS -> {
                MapCityNavigation(navController, mainViewModel)
            }
            LoadingStatus.ERROR -> {
                APIResultErrorDialog(
                    baseErrorResId = R.string.error_cityloading,
                    result = mainState.loadingResult,
                    onRetry = { mainViewModel.downloadCities() },
                    onCancel = { activity?.finish() }
                )
            }
        }
    }
}
