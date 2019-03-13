package dimaarts.ru.cleanarchitecturesampleapp.presenter.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.domain.usecase.PokemonUseCase
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : MvpPresenter<MainView>() {
    @Inject
    lateinit var useCase: PokemonUseCase

    override fun attachView(view: MainView?) {
        super.attachView(view)
    }

    fun searchPokemon(query: String) {
            useCase.dispose()
            viewState?.clear()
            useCase.searchPokemon(Subscriber(), PokemonUseCase.Query(query))
    }

    override fun onDestroy() {
        super.onDestroy()
        useCase.dispose()
    }

    inner class Subscriber: DisposableSubscriber<PokemonEntity>() {
        override fun onComplete() {

        }

        override fun onNext(pokemon: PokemonEntity) {
            viewState?.addPokemon(pokemon)
        }

        override fun onError(e: Throwable) {
            viewState?.showSearchError()
        }

    }

}