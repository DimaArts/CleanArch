package dimaarts.ru.cleanarchitecturesampleapp.di.component

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dimaarts.ru.cleanarchitecturesampleapp.PokeApplication
import dimaarts.ru.cleanarchitecturesampleapp.di.module.AppModule
import dimaarts.ru.cleanarchitecturesampleapp.di.module.UIModule
import dimaarts.ru.data.net.api.di.ApiClientModule
import dimaarts.ru.data.net.api.di.PicassoModule
import dimaarts.ru.data.repository.di.RepositoryModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ApiClientModule::class, RepositoryModule::class, PicassoModule::class, UIModule::class])
@Singleton
interface AppComponent : AndroidInjector<PokeApplication> {
    fun inject(application: Application)
    fun context(): Context
    /*@Component.Builder
    abstract class Builder : AndroidInjector.Builder<PokeApplication>() {
        @BindsInstance
        abstract fun application(application: PokeApplication): Builder
    }*/
}