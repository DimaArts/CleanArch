package dimaarts.ru.data.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dimaarts.ru.data.repository.database.Pokemon
import io.reactivex.Flowable

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAll(): Flowable<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Pokemon>)

    @Query("SELECT * FROM pokemon where name like :query order by name desc")
    fun searchPokemon(query: String): Flowable<List<Pokemon>>

}