package dimaarts.ru.cleanarchitecturesampleapp.presenter.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity

interface MainView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addPokemon(pokemon: PokemonEntity)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSearchError()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun clear()
}