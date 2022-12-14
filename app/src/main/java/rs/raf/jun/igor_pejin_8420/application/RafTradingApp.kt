package rs.raf.jun.igor_pejin_8420.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.jun.igor_pejin_8420.modules.coreModule
import rs.raf.jun.igor_pejin_8420.modules.popstocks_module
import rs.raf.jun.igor_pejin_8420.modules.tradenews_module
import timber.log.Timber

class RafTradingApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            tradenews_module,
            popstocks_module
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@RafTradingApp)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}