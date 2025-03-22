package ignacio.guerendiain.uala.app

import android.content.res.Configuration
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ignacio.guerendiain.uala.app.NavigationUtils.DEFAULT_ANIMATION_DURATION
import ignacio.guerendiain.uala.app.NavigationUtils.enterFromRightExitToLeft
import ignacio.guerendiain.uala.feature.cities.ui.CityListContent
import ignacio.guerendiain.uala.feature.cities.ui.CityListScreen
import ignacio.guerendiain.uala.feature.map.ui.MapScreen
import ignacio.guerendiain.uala.feature.map.ui.MapContent

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
    val portrait = LocalContext
        .current
        .resources
        .configuration
        .orientation == Configuration.ORIENTATION_PORTRAIT

    val navController = rememberNavController()

    if (portrait) RootPortraitNavigation(navController)
    else LandscapeLayout()
}

@Composable
fun LandscapeLayout(){
    Row(
        modifier = Modifier.fillMaxSize()
    ){
        CityListContent()
        MapContent()
    }
}

@Composable
fun RootPortraitNavigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = RootNavDestination.CITYLISTSCREEN.name
    ){
        enterFromRightExitToLeft(
            route = RootNavDestination.CITYLISTSCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) },
        ){
            CityListScreen()
        }

        enterFromRightExitToLeft(
            route = "${RootNavDestination.MAPSCREEN.name}/{${RootNavArguments.ARGS_CITYID}}",
            arguments = RootNavDestination.MAPSCREEN.arguments
        ){
            val cityId = it
                .arguments
                ?.getLong(RootNavArguments.ARGS_CITYID)
                ?:-1

            if (cityId > 0) MapScreen()
        }
    }
}