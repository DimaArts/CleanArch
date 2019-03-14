package dimaarts.ru.domain.di

import dagger.Module
import dagger.Provides
import dimaarts.ru.data.net.api.client.ApiClient
import dimaarts.ru.data.repository.PokemonRepository
import dimaarts.ru.domain.interactors.PokemonInteractor
import dimaarts.ru.domain.usecase.PokemonUseCase
import io.reactivex.Scheduler
import javax.inject.Named

@Module
class PokemonUseCaseModule {
    @Provides
    fun providePokemonUseCaseModule(apiClient: ApiClient, repository: PokemonRepository, @Named("scheduler") executor: Scheduler, @Named("postScheduler") postExecutor: Scheduler): PokemonUseCase {
        return PokemonInteractor(apiClient, repository, executor, postExecutor)
    }
}
