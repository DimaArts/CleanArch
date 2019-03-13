package dimaarts.ru.data.net.api

import dimaarts.ru.data.entity.pokemon.PokemonListEntity
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("$POKEMON_API_VERSION/pokemon")
    fun getPokemonPaged(@Query("offset") offset: Int, @Query("limit") limit: Int = 20): Single<PokemonListEntity>
    @GET("$POKEMON_API_VERSION/pokemon/{id}")
    fun getPokemonDetail(@Path("id") id: Int): Single<PokemonEntity>
}