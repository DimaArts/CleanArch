package dimaarts.ru.domain.interactors

import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.net.api.client.ApiClient
import dimaarts.ru.domain.exception.EmptyQueryException
import dimaarts.ru.domain.exception.PokemonNotFoundException
import dimaarts.ru.domain.mapper.PokemonMapper
import dimaarts.ru.domain.usecase.PokemonUseCase
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.subscribers.DisposableSubscriber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class PokemonInteractor
@Inject
constructor(private val apiClient: ApiClient,
            @Named("scheduler") scheduler: Scheduler,
            @Named("postScheduler") postScheduler: Scheduler
) : PokemonUseCase(scheduler, postScheduler) {

    override fun searchPokemon(
        observer: DisposableSubscriber<PokemonEntity>,
        query: Query
    ) {
        execute(observer, query) {
            when {
                query.query.isEmpty() -> Flowable.error(EmptyQueryException())
                apiClient.hasInternetConnection() -> apiClient.getPokemonList()
                    .flatMap { x-> if(x.isEmpty()) throw PokemonNotFoundException() else Flowable.fromIterable(x) }
                    .filter{x -> x.name.contains(query.query)}
                    .map(PokemonMapper::map)
                    .flatMap { x -> apiClient.getPokemonDetail(x.id).toFlowable() }
                else -> {
                    val list: List<PokemonEntity> = arrayListOf()
                    Flowable.fromArray(list)
                        .flatMap { x-> Flowable.fromIterable(x) }
                }
            }
        }
    }
}