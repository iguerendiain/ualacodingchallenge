package ignacio.guerendiain.uala.feature.cities

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.app.MainState
import ignacio.guerendiain.uala.app.MainViewModel
import ignacio.guerendiain.uala.app.NavigationUtils.DEFAULT_ANIMATION_DURATION
import ignacio.guerendiain.uala.app.NavigationUtils.enterFromRightExitToLeft
import ignacio.guerendiain.uala.app.RootNavArguments
import ignacio.guerendiain.uala.app.RootNavDestination
import ignacio.guerendiain.uala.core.ui.common.ScreenTitle
import ignacio.guerendiain.uala.core.ui.common.Toolbar
import ignacio.guerendiain.uala.core.ui.util.isPortrait
import ignacio.guerendiain.uala.feature.cities.ui.CityListContent
import ignacio.guerendiain.uala.feature.cities.ui.CityListScreen
import ignacio.guerendiain.uala.feature.cities.ui.MapContent
import ignacio.guerendiain.uala.feature.cities.ui.MapScreen

@Composable
fun MapCityNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val mainState by mainViewModel.state.collectAsStateWithLifecycle()
    val cityListScrollState = rememberLazyListState()

    if (isPortrait()) RootPortraitNavigation(
        navController = navController,
        mainViewModel = mainViewModel,
        searchQuery = "",
        cityListState = cityListScrollState,
        onSearch = {  }
    )
    else LandscapeLayout(
        mainState = mainState,
        searchQuery = "",
        listState = cityListScrollState,
        onSearch = {  }
    )
}


@Composable
fun LandscapeLayout(
    mainState: MainState,
    searchQuery: String,
    listState: LazyListState,
    onSearch: (query: String) -> Unit,
){
    Column(modifier = Modifier
        .fillMaxSize()
        .safeContentPadding()) {
        Toolbar(
            startContent = {},
            centerContent = {
                ScreenTitle(titleRes = R.string.citylist_title)
            },
            endContent = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)){
                CityListContent(
                    portraitMode = false,
                    searchQuery = searchQuery,
                    cities = mainState.citiesResponse.response ?: listOf(),
                    listState = listState,
                    showingFavorites = false,

                    onSearch = onSearch,
                    onCloseKeyboard = { },
                    onCitySelected = { },
                    onFavoriteToggle = { },
                    onToggleShowFavorites = { }
                )
            }
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(1f)) {
                MapContent()
            }
        }
    }
}

@Composable
fun RootPortraitNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    searchQuery: String,
    cityListState: LazyListState,
    onSearch: (query: String) -> Unit
){
    NavHost(
        navController = navController,
        startDestination = RootNavDestination.CITYLISTSCREEN.name
    ){
        enterFromRightExitToLeft(
            route = RootNavDestination.CITYLISTSCREEN.name,
            enterTransition = { fadeIn(animationSpec = tween(DEFAULT_ANIMATION_DURATION)) },
        ){
            CityListScreen(
                mainViewModel = mainViewModel,
                searchQuery = searchQuery,
                listState = cityListState,
                onSearch = onSearch
            )
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