package dimaarts.ru.cleanarchitecturesampleapp.presenter.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.domain.result.PokemonSearchResult
import dimaarts.ru.domain.usecase.PokemonUseCase
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : MvpPresenter<MainView>() {
    @Inject
    lateinit var useCase: PokemonUseCase

    override fun attachView(view: MainView?) {
        super.attachView(view)
    }

    fun getDetail(entity: PokemonEntity) {
        useCase.getDetail(Subscriber(), PokemonUseCase.Query(id=entity.id))
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

    inner class Subscriber: DisposableSingleObserver<PokemonSearchResult>() {
        override fun onSuccess(result: PokemonSearchResult) {
            val all = result.list
            if(all!=null) {
                viewState?.updatePokemons(all, result.diffResult)
            }
        }

        override fun onError(e: Throwable) {
            viewState?.showSearchError()
        }

    }

}