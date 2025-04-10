package uk.ac.tees.mad.token.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.token.data.local.FavoriteTokenDao
import uk.ac.tees.mad.token.data.local.TokenDatabase
import uk.ac.tees.mad.token.data.remote.CryptoApiService
import uk.ac.tees.mad.token.data.repository.Repository
import uk.ac.tees.mad.token.data.repository.RepositoryImp
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExecutor(): Executor = Executors.newSingleThreadExecutor()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCryptoApiService(retrofit: Retrofit): CryptoApiService {
        return retrofit.create(CryptoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TokenDatabase {
        return TokenDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteTokenDao(database: TokenDatabase): FavoriteTokenDao {
        return database.favoriteTokenDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: CryptoApiService,
        favoriteTokenDao: FavoriteTokenDao
    ): Repository {
        return RepositoryImp(api, favoriteTokenDao)
    }
}