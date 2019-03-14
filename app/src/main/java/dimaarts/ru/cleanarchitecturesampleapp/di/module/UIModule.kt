package dimaarts.ru.cleanarchitecturesampleapp.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.ActivityScope
import dimaarts.ru.cleanarchitecturesampleapp.di.scope.FragmentScope
import dimaarts.ru.cleanarchitecturesampleapp.ui.main.MainActivity
import dimaarts.ru.cleanarchitecturesampleapp.ui.main.MainFragment
import dimaarts.ru.domain.di.PokemonUseCaseModule


@Module
interface UIModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [PokemonUseCaseModule::class])
    fun buildMainFragment(): MainFragment
    @ActivityScope
    @ContributesAndroidInjector
    fun buildMainActivity(): MainActivity
}