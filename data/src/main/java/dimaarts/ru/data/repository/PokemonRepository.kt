package dimaarts.ru.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.repository.dao.PokemonDao
import dimaarts.ru.data.repository.database.Pokemon
import dimaarts.ru.data.repository.mapper.PokemonDatabaseMapper
import io.reactivex.Flowable
import io.reactivex.Single

@Database(entities = [Pokemon::class], version = 2)
abstract class PokemonRepository: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    fun insert(element: PokemonEntity) {
        pokemonDao().insert(PokemonDatabaseMapper.map(element))
    }

    fun insertAll(list: List<PokemonEntity>) {
        pokemonDao().insertAll(list.map(PokemonDatabaseMapper::map))
    }

    fun searchPokemon(query: String): Single<List<PokemonEntity>> {
        return pokemonDao().searchPokemon("%$query%").map (PokemonDatabaseMapper::mapFrom)
    }

    fun getPokemon(id: Int): Single<PokemonEntity> {
        return pokemonDao().getPokemon(id).map (PokemonDatabaseMapper::mapFrom)
    }

    val all: Flowable<List<PokemonEntity>> get() {
        return pokemonDao().all().map (PokemonDatabaseMapper::mapFrom)
    }

    companion object {
        private const val DB_NAME = "pokemonDb"

        fun getRepository(context: Context): PokemonRepository {
            return Room.databaseBuilder<PokemonRepository>(context, PokemonRepository::class.java, DB_NAME).addMigrations(MIGRATION_1_2).build()
        }

        private val MIGRATION_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE pokemon ADD COLUMN loadingError TEXT")
                database.execSQL("ALTER TABLE pokemon ADD COLUMN haveDetailInfo INTEGER DEFAULT 0 NOT NULL")
            }
        }
    }
}