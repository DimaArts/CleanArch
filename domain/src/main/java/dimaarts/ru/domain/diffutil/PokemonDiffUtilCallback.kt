package dimaarts.ru.domain.diffutil

import androidx.recyclerview.widget.DiffUtil
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity


class PokemonDiffUtilCallback: DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem == newItem
    }

}