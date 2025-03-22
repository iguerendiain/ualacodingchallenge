package ignacio.guerendiain.uala.feature.cities.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.domain.model.City
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

    onSearch: (query: String) -> Unit,
    onCloseKeyboard: () -> Unit
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
                endContent = {}
            )
        }

        SearchField(
            value = searchQuery,
            hint = stringResource(R.string.citylist_searchhint),
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onSearch,
            onKeyboardDone = onCloseKeyboard
        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(cities.size){
                val city = cities[it]
                CityListItem(city.name)
                if (it < cities.size) Divider()
            }

            item { NavbarSpacer() }
        }
    }
}