package dimaarts.ru.data.net.api.client

import dimaarts.ru.data.entity.pokemon.InfoEntity
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface BaseApiClient {
    fun getPokemonList(): Flowable<List<InfoEntity>>
    fun getPokemonDetail(id: Int): Single<PokemonEntity>
}