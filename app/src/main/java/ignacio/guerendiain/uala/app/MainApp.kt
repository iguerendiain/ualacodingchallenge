package ignacio.guerendiain.uala.app

import android.app.Application
import ignacio.guerendiain.uala.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@MainApp) }
    }
}