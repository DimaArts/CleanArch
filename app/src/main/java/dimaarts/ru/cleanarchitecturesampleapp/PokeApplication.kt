package dimaarts.ru.cleanarchitecturesampleapp

import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dimaarts.ru.data.net.api.di.ApiClientModule
import dimaarts.ru.cleanarchitecturesampleapp.di.module.AppModule
import dimaarts.ru.cleanarchitecturesampleapp.di.component.DaggerAppComponent

class PokeApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().apiClientModule(ApiClientModule()).appModule(
            AppModule(
                this
            )
        ).build()
    }
}