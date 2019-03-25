package dimaarts.ru.cleanarchitecturesampleapp.presenter.main

import androidx.recyclerview.widget.DiffUtil
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity

interface MainView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updatePokemons(pokemonList: List<PokemonEntity>, diffResult: DiffUtil.DiffResult?)
    @StateStrategyType(SkipStrategy::class)
    fun showSearchError()
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun clear()
}