package test.kieu.country.di

import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.kieu.country.network.RetrofitConstants
import test.kieu.country.db.CountryDatabase
import test.kieu.country.network.CountryApi
import test.kieu.country.network.CountryApiService
import test.kieu.country.repository.CountryRepository
import test.kieu.country.usecase.GetAllCountryUseCase

@Stable
@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideCountryApi(retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideCountryApiService(countryApi: CountryApi): CountryApiService {
        return CountryApiService(countryApi)
    }


    @Provides
    @ViewModelScoped
    fun provideCountryRepository(countryApiService: CountryApiService): CountryRepository {
        return CountryRepository(countryApiService)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllCountryUseCase(countryRepository: CountryRepository, countryDatabase: CountryDatabase): GetAllCountryUseCase {
        return GetAllCountryUseCase(countryRepository, countryDatabase)
    }
}