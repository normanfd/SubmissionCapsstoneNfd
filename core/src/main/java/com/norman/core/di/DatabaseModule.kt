package com.norman.core.di

import android.content.Context
import androidx.room.Room
import com.norman.core.data.source.local.room.MovieDao
import com.norman.core.data.source.local.room.MovieDatabase
import com.norman.core.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("norman".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }
}