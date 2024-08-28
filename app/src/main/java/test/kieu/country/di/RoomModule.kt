package test.kieu.country.di

import android.content.Context
import androidx.compose.runtime.Stable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.kieu.country.db.CountryDBImpl
import test.kieu.country.db.CountryDBRepository
import test.kieu.country.db.CountryDatabase
import javax.inject.Singleton

@Stable
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideCountryDatabase(
        @ApplicationContext context: Context
    ): CountryDatabase {
        return CountryDatabase.getDatabase(context)
    }



    @Provides
    @Singleton
    fun provideCountryDBRepository(countryDB:CountryDatabase) : CountryDBRepository {
        return CountryDBImpl(countryDB.countryDao())
    }

}