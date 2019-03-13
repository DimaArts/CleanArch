package dimaarts.ru.data.net.api.client

import android.content.Context
import dimaarts.ru.data.entity.pokemon.InfoEntity
import dimaarts.ru.data.entity.pokemondetails.PokemonEntity
import dimaarts.ru.data.net.api.PokeApi
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import android.net.ConnectivityManager



class ApiClient(url: String, val context: Context, client: OkHttpClient) : BaseApiClient {

    private var retrofitClient: PokeApi = Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(PokeApi::class.java)

    override fun getPokemonList(): Flowable<List<InfoEntity>> {
        return retrofitClient.getPokemonPaged(0, 9999).toFlowable().map { x -> x.results }
    }

    override fun getPokemonDetail(id: Int): Single<PokemonEntity> {
        return retrofitClient.getPokemonDetail(id)
    }

    fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}