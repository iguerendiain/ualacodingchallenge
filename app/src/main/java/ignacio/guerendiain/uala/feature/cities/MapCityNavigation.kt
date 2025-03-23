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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ignacio.guerendiain.uala.R
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
import ignacio.guerendiain.uala.feature.cities.vm.CityListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapCityNavigation(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    cityListViewModel: CityListViewModel = koinViewModel<CityListViewModel>()
) {
    val mainState by mainViewModel.state.collectAsStateWithLifecycle()
    val cityListScrollState = rememberLazyListState()

    LaunchedEffect(mainState.loadingResult) {
        cityListViewModel.filterCities()
    }

    if (isPortrait()) RootPortraitNavigation(
        navController = navController,
        cityListViewModel = cityListViewModel,
        listState = cityListScrollState
    )
    else LandscapeLayout(
        cityListViewModel = cityListViewModel,
        listState = cityListScrollState
    )
}


@Composable
fun LandscapeLayout(
    cityListViewModel: CityListViewModel,
    listState: LazyListState
){
    val cityListState by cityListViewModel.state.collectAsStateWithLifecycle()

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
                    searchQuery = cityListState.searchQuery?:"",
                    cities = cityListState.listedCities,
                    listState = listState,
                    showingFavorites = false,
                    loadingStatus = cityListState.loadingStatus,

                    onSearch = {
                        cityListViewModel.setSearchQuery(it)
                        cityListViewModel.filterCities()
                    },
                    onCloseKeyboard = { },
                    onCitySelected = { },
                    onFavoriteToggle = { cityListViewModel.toggleFavorite(it) },
                    onToggleShowFavorites = {
                        cityListViewModel.toggleFilterFavories()
                        cityListViewModel.filterCities()
                    }
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
    cityListViewModel: CityListViewModel,
    listState: LazyListState
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
                navController = navController,
                cityListViewModel = cityListViewModel,
                listState = listState
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