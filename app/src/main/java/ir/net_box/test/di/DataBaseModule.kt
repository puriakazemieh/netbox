package ir.net_box.test.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.net_box.test.data.local.db.NetboxDatabase
import ir.net_box.test.data.local.db.dao.PlaylistDao
import ir.net_box.test.data.local.db.dao.VideoDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideNetboxDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, NetboxDatabase::class.java, "netbox.db")
            .allowMainThreadQueries()
            .build()


    @Provides
    @Singleton
    fun provideMovieDao(
        database: NetboxDatabase
    ): PlaylistDao = database.playlistDao()


    @Provides
    @Singleton
    fun provideVideoDao(
        database: NetboxDatabase
    ): VideoDao = database.videoDao()
}