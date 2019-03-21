package dimaarts.ru.domain.mapper

import androidx.recyclerview.widget.DiffUtil
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.domain.result.PokemonSearchResult
import dimaarts.ru.domain.diffutil.PokemonDiffUtilCallback

object SearchResultMapper {
    fun map(pel: List<PokemonEntity>, oldList: List<PokemonEntity>): PokemonSearchResult {
        val searchResult = PokemonSearchResult()
        searchResult.list = pel
        val pokemonDiffUtilCallback = PokemonDiffUtilCallback(oldList, pel)
        searchResult.diffResult = DiffUtil.calculateDiff(pokemonDiffUtilCallback)
        return  searchResult
    }
}