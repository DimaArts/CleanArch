package dimaarts.ru.domain.mapper

import dimaarts.ru.data.entity.pokemon.InfoEntity
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.repository.database.Pokemon

object PokemonMapper {
    fun map(pe: InfoEntity): PokemonEntity {
        val id = pe.url.trimEnd('/').split("/").last().toInt()
        return PokemonEntity(id, pe.name)
    }
}