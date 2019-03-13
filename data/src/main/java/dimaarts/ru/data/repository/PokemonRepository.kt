package dimaarts.ru.data.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
abstract class PokemonRepository: RoomDatabase() {

    @Inject
    fun getRepository(context: Context): PokemonRepository {
        return Room.databaseBuilder<PokemonRepository>(context, PokemonRepository::class.java, DB_NAME).build()
    }

    companion object {
        const val DB_NAME = "pokemonDb"
    }
}