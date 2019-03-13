package dimaarts.ru.data.entity.pokemondetails

data class PokemonEntity (
    val id: Int,
    val name: String? = null,
    val height: Int? = null,
    val sprites: SpritesEntity? = null
)