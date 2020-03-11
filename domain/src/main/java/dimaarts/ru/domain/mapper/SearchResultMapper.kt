package dimaarts.ru.domain.mapper

import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.domain.result.PokemonSearchResult


object SearchResultMapper {

    fun map(pel: List<PokemonEntity>): PokemonSearchResult {
        val searchResult = PokemonSearchResult()
        searchResult.newList = pel
        return searchResult
    }
}