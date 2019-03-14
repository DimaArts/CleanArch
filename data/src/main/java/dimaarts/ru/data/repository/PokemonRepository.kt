package dimaarts.ru.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.repository.dao.PokemonDao
import dimaarts.ru.data.repository.database.Pokemon
import dimaarts.ru.data.repository.mapper.PokemonDatabaseMapper
import io.reactivex.Flowable

@Database(entities = [Pokemon::class], version = 1)
abstract class PokemonRepository: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    fun insert(element: PokemonEntity) {
        pokemonDao().insert(PokemonDatabaseMapper.map(element))
    }

    fun searchPokemon(query: String): Flowable<List<PokemonEntity>> {
        return pokemonDao().searchPokemon("%$query%").map (PokemonDatabaseMapper::mapFrom)
    }

    val all: Flowable<List<PokemonEntity>> get() {
        return pokemonDao().all().map (PokemonDatabaseMapper::mapFrom)
    }

    companion object {
        private const val DB_NAME = "pokemonDb"

        fun getRepository(context: Context): PokemonRepository {
            return Room.databaseBuilder<PokemonRepository>(context, PokemonRepository::class.java, DB_NAME).build()
        }
    }
}