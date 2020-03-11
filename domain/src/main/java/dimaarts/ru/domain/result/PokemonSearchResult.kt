package dimaarts.ru.domain.result

import androidx.recyclerview.widget.AsyncListDiffer
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import androidx.recyclerview.widget.DiffUtil

class PokemonSearchResult {
    var newList: List<PokemonEntity>? = null
}