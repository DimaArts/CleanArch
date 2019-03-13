package dimaarts.ru.cleanarchitecturesampleapp.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dimaarts.ru.cleanarchitecturesampleapp.PokeApplication
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(val application: PokeApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    @Named("scheduler")
    fun provideScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Named("postScheduler")
    fun providePostScheduler(): Scheduler = AndroidSchedulers.mainThread()
}