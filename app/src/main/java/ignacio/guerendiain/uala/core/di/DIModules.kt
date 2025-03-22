package ignacio.guerendiain.uala.core.di

import androidx.core.content.ContextCompat
import ignacio.guerendiain.uala.R
import ignacio.guerendiain.uala.core.domain.repository.CityRepository
import ignacio.guerendiain.uala.core.domain.repositoryimpl.CityRepositoryRoomDBRetrofit
import ignacio.guerendiain.uala.core.network.buildCityCalls
import ignacio.guerendiain.uala.core.storage.buildRoomDB
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDIModule = module {
    single { buildRoomDB(get()) }
    single { CityRepositoryRoomDBRetrofit(db = get(), cityCalls = get()) }.bind<CityRepository>()
    single { buildCityCalls(ContextCompat.getString(get(), R.string.api_base_url)) }
}