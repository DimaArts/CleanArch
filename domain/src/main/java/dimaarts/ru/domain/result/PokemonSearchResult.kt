package dimaarts.ru.domain.result

import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import androidx.recyclerview.widget.DiffUtil

class PokemonSearchResult {
    var list: List<PokemonEntity>? = null
    var diffResult: DiffUtil.DiffResult? = null
}