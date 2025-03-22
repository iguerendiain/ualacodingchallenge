package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ignacio.guerendiain.uala.app.MainViewModel
import ignacio.guerendiain.uala.core.ui.util.isPortrait

@Composable
fun CityListScreen(
    mainViewModel: MainViewModel,
    searchQuery: String,
    listState: LazyListState,
    onSearch: (query: String) -> Unit
){
    val mainState by mainViewModel.state.collectAsStateWithLifecycle()

    CityListContent(
        portraitMode = isPortrait(),
        searchQuery = searchQuery,
        cities = mainState.citiesResponse.response?: listOf(),
        listState = listState,

        onSearch = onSearch,
        onCloseKeyboard = {}
    )
}