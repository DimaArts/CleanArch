package dimaarts.ru.domain.usecase

import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import io.reactivex.Scheduler
import io.reactivex.subscribers.DisposableSubscriber

abstract class PokemonUseCase constructor(
    executor: Scheduler,
    postExecutor: Scheduler
): UseCase<PokemonEntity, PokemonUseCase.Query>(executor, postExecutor) {

    abstract fun searchPokemon(observer: DisposableSubscriber<PokemonEntity>, query: Query)

    data class Query(val query: String)
}