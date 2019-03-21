package dimaarts.ru.domain.diffutil

import androidx.recyclerview.widget.DiffUtil
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity


class PokemonDiffUtilCallback(private val oldList: List<PokemonEntity>, private val newList: List<PokemonEntity>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPokemon = oldList[oldItemPosition]
        val newPokemon = newList[newItemPosition]
        return oldPokemon.id == newPokemon.id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPokemon = oldList[oldItemPosition]
        val newPokemon = newList[newItemPosition]
        return oldPokemon == newPokemon
    }

}