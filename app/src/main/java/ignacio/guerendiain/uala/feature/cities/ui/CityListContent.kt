package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.domain.model.City
import ignacio.guerendiain.uala.core.ui.button.ToolbarTextButton
import ignacio.guerendiain.uala.core.ui.common.Divider
import ignacio.guerendiain.uala.core.ui.common.ScreenTitle
import ignacio.guerendiain.uala.core.ui.common.SearchField
import ignacio.guerendiain.uala.core.ui.common.Toolbar
import ignacio.guerendiain.uala.core.ui.util.NavbarSpacer
import ignacio.guerendiain.uala.core.ui.util.StatusbarSpacer

@Composable
fun CityListContent(
    portraitMode: Boolean,
    searchQuery: String,
    cities: List<City>,
    listState: LazyListState,
    showingFavorites: Boolean,

    onSearch: (query: String) -> Unit,
    onCloseKeyboard: () -> Unit,
    onCitySelected: (Long) -> Unit,
    onFavoriteToggle: (Long) -> Unit,
    onToggleShowFavorites: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        if (portraitMode){
            StatusbarSpacer()
            Toolbar(
                startContent = {},
                centerContent = {
                    ScreenTitle(titleRes = R.string.citylist_title)
                },
                endContent = {
                    ToolbarTextButton(res =
                        if (showingFavorites) R.string.citylist_all
                        else R.string.citylist_favorites
                    )
                }
            )
        }

        SearchField(
            value = searchQuery,
            hint = stringResource(R.string.citylist_searchhint),
            modifier = Modifier.fillMaxWidth().padding(horizontal=20.dp),
            onValueChange = onSearch,
            onKeyboardDone = onCloseKeyboard
        )

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(cities.size){
                val city = cities[it]
                CityListItem(
                    name = city.name,
                    country = city.country,
                    isFavorite = false,
                    lat = city.lat,
                    lon = city.lon,
                    modifier = Modifier.clickable {
                        onCitySelected(city.id)
                    },
                    onFavoriteToggle = {
                        onFavoriteToggle(city.id)
                    }
                )
                if (it < cities.size) Divider()
            }

            item { NavbarSpacer() }
        }
    }
}