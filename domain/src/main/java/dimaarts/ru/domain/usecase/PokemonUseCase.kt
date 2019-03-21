package dimaarts.ru.domain.usecase

import dimaarts.ru.domain.result.PokemonSearchResult
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver

abstract class PokemonUseCase constructor(
    executor: Scheduler,
    postExecutor: Scheduler
): UseCase<PokemonSearchResult, PokemonUseCase.Query>(executor, postExecutor) {

    abstract fun searchPokemon(observer: DisposableSingleObserver<PokemonSearchResult>, query: Query)
    abstract fun getDetail(observer: DisposableSingleObserver<PokemonSearchResult>, query: Query)

    data class Query(val query: String? = null, val id: Int? = null)
}