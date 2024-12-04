package ir.net_box.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ir.net_box.test.domin.NetboxRepository
import ir.net_box.test.domin.usecase.GetPlaylistUseCase
import ir.net_box.test.domin.usecase.UseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @ViewModelScoped
    @Provides
    fun provideCases(
        repository: NetboxRepository,
    ): UseCase {
        return UseCase(
            getPlaylistUseCase = GetPlaylistUseCase(repository)
        )
    }
}