package dimaarts.ru.cleanarchitecturesampleapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.PerActivity
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.PerFragment
import dimaarts.ru.cleanarchitecturesampleapp.ui.main.MainActivity
import dimaarts.ru.cleanarchitecturesampleapp.ui.main.MainFragment
import dimaarts.ru.domain.di.PokemonUseCaseModule


@Module
interface UIModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [PokemonUseCaseModule::class])
    fun buildMainFragment(): MainFragment
    @PerActivity
    @ContributesAndroidInjector
    fun buildMainActivity(): MainActivity
}