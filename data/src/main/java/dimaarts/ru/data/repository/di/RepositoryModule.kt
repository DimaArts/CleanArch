package dimaarts.ru.data.repository.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dimaarts.ru.data.repository.PokemonRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(context: Context): PokemonRepository {
        return PokemonRepository.getRepository(context)
    }
}