package dimaarts.ru.data.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dimaarts.ru.data.repository.database.Pokemon
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun all(): Flowable<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: Pokemon)

    @Query("SELECT * FROM pokemon where name like :query order by name desc")
    fun searchPokemon(query: String): Flowable<List<Pokemon>>

    @Query("SELECT * FROM pokemon where id=:id")
    fun getPokemon(id: Int): Single<Pokemon>
}