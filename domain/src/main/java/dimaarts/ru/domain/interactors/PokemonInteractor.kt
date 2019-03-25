package dimaarts.ru.domain.interactors

import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.net.api.client.ApiClient
import dimaarts.ru.data.repository.PokemonRepository
import dimaarts.ru.domain.mapper.SearchResultMapper
import dimaarts.ru.domain.result.PokemonSearchResult
import dimaarts.ru.domain.exception.EmptyQueryException
import dimaarts.ru.domain.exception.PokemonNotFoundException
import dimaarts.ru.domain.mapper.PokemonMapper
import dimaarts.ru.domain.usecase.PokemonUseCase
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class PokemonInteractor
@Inject
constructor(private val apiClient: ApiClient,
            private val repository: PokemonRepository,
            @Named("scheduler") scheduler: Scheduler,
            @Named("postScheduler") postScheduler: Scheduler
) : PokemonUseCase(scheduler, postScheduler) {
    var lastSearchResult = listOf<PokemonEntity>()

    override fun getDetail(observer: DisposableSingleObserver<PokemonSearchResult>, query: Query) {
        execute(observer, query) {
            when {
                query.id == null -> Single.error(EmptyQueryException())
                apiClient.hasInternetConnection() -> {
                    apiClient.getPokemonDetail(query.id).doOnSuccess { x ->
                        x.detailLoaded = true
                        repository.insert(x)
                    }
                        .toFlowable()
                        .sorted { o1, o2 ->
                            val name1 = o1.name
                            val name2 = o2.name
                            if(name1 == null || name2 == null) -1
                            else name1.compareTo(name2)}
                        .flatMap { x -> Flowable.fromIterable(lastSearchResult).map { y ->
                            y.loadingError = null
                            if(y.id == x.id) x else y } }
                        .toList()
                        .map{SearchResultMapper.map(it, lastSearchResult)}
                        .doOnSuccess { x -> lastSearchResult=x.list ?: arrayListOf() }
                        .timeout(
                            DATA_REQUEST_TIMEOUT,
                            TimeUnit.MILLISECONDS
                        )
                        .onErrorReturn {e ->
                            Flowable.fromIterable(lastSearchResult)
                                .map {
                                    x -> if(query.id == x.id) {
                                        x.copy(loadingError = "Content loading error")
                                    }
                                    else
                                     x
                                }
                                .toList()
                                .map{SearchResultMapper.map(it, lastSearchResult)}
                                .doOnSuccess { x -> lastSearchResult=x.list ?: arrayListOf() }
                                .blockingGet()
                        }
                }

                else -> repository.getPokemon(query.id)
                    .toFlowable()
                    .flatMap { x -> Flowable.fromIterable(lastSearchResult).map { y -> if(y.id == x.id) x else y } }
                    .map { x ->
                        if (!x.detailLoaded && query.id == x.id) {
                            x.copy(loadingError = "Content loading error")
                        }
                        else
                            x
                    }
                    .toList()
                    .map{SearchResultMapper.map(it, lastSearchResult)}
                    .doOnSuccess { x -> lastSearchResult=x.list ?: arrayListOf() }
                    .timeout(
                        DATA_REQUEST_TIMEOUT,
                        TimeUnit.MILLISECONDS
                    )
            }

        }
    }

    override fun searchPokemon(
        observer: DisposableSingleObserver<PokemonSearchResult>,
        query: Query
    ) {
        execute(observer, query) {
            when {
                query.query==null || query.query.isEmpty() -> Single.error(EmptyQueryException())
                apiClient.hasInternetConnection() -> apiClient.getPokemonList()
                    .flatMap { x-> if(x.isEmpty()) throw PokemonNotFoundException() else Flowable.fromIterable(x) }
                    .filter{x -> x.name.contains(query.query.toLowerCase())}
                    .map(PokemonMapper::map)
                    .toList()
                    .doOnSuccess {x ->
                        repository.insertAll(x)
                    }
                    .map{SearchResultMapper.map(it, lastSearchResult)}
                    .doOnSuccess { x -> lastSearchResult=x.list ?: arrayListOf() }
                    .timeout(DATA_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                else -> {
                    repository.searchPokemon(query.query.toLowerCase())
                        .map{SearchResultMapper.map(it, lastSearchResult)}
                        .doOnSuccess { x -> lastSearchResult=x.list ?: arrayListOf() }
                        .timeout(DATA_REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                }
            }
        }
    }

    companion object {
        const val DATA_REQUEST_TIMEOUT = 2000L
    }
}