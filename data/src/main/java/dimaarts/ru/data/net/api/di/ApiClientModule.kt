package dimaarts.ru.data.net.api.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dimaarts.ru.data.net.api.POKEMON_API_URL
import dimaarts.ru.data.net.api.client.ApiClient
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class ApiClientModule {
    @Singleton
    @Provides
    fun provideApiClient(context: Context, client: OkHttpClient): ApiClient {
        return ApiClient(POKEMON_API_URL, context, client)
    }
}