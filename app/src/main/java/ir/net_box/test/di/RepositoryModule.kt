package ir.net_box.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.net_box.test.data.NetboxRepositoryImp
import ir.net_box.test.data.local.db.NetboxDatabase
import ir.net_box.test.data.remote.ApiService
import ir.net_box.test.domin.NetboxRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        api: ApiService,
        db: NetboxDatabase
    ): NetboxRepository {
        return NetboxRepositoryImp(
            apiService = api,
            playlistDao = db.movieDao()
        )
    }

}