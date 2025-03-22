package ignacio.guerendiain.uala.core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(cfg: KoinAppDeclaration? = null){
    startKoin {
        cfg?.invoke(this)
        modules(commonDIModule)
    }
}