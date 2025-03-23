package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import ignacio.guerendiain.uala.core.ui.util.isPortrait
import ignacio.guerendiain.uala.feature.cities.vm.CityListViewModel

@Composable
fun CityListScreen(
    navController: NavHostController,
    cityListViewModel: CityListViewModel,
    listState: LazyListState
){
    val cityListState by cityListViewModel.state.collectAsStateWithLifecycle()

    CityListContent(
        portraitMode = isPortrait(),
        searchQuery = cityListState.searchQuery ?: "",
        cities = cityListState.listedCities,
        listState = listState,
        showingFavorites = cityListState.filterFavorites,
        loadingStatus = cityListState.loadingStatus,

        onSearch = {
            cityListViewModel.setSearchQuery(it)
            cityListViewModel.filterCities()
        },
        onCloseKeyboard = {},
        onCitySelected = {},
        onFavoriteToggle = { cityListViewModel.toggleFavorite(it) },
        onToggleShowFavorites = {
            cityListViewModel.toggleFilterFavories()
            cityListViewModel.filterCities()
        },
    )
}