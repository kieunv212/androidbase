package test.kieu.country.usecase

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import test.kieu.country.db.CountryDatabase
import test.kieu.country.model.Country
import test.kieu.country.network.Resource
import test.kieu.country.repository.CountryRepository
import java.io.IOException
import javax.inject.Inject

class GetAllCountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository,
    private val countryDatabase: CountryDatabase
){
    suspend operator fun invoke(): Flow<Resource<List<Country>?>> = flow {
        try {
            emit(Resource.Loading())
            val response = countryRepository.getCountrys()
            if(response.isNotEmpty()){
                clearTable()
                response.map { country ->
                    countryDatabase.countryDao().insert(country.toCountryEntity())
                }
                emit(Resource.Success(response))
            }
            getDataDB()
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error!"))
        } catch (e: IOException) {
            // Load cache data if there is not internet connection
            emit(Resource.Success(getDataDB()))

        }
    }

    private suspend fun getDataDB(): List<Country> {
        return withContext(Dispatchers.IO) {
            val list = countryDatabase.countryDao().getAll()
            var listCountry = emptyList<Country>()
            if (list.isNotEmpty()) {
                listCountry = list.map { entity ->
                    entity.toCountry()
                }
                Log.d("listCountrysize", listCountry.size.toString())
                Log.d("listCountry", listCountry.toString())
            }

            return@withContext listCountry
        }
    }

    private suspend fun clearTable() {
        return withContext(Dispatchers.IO) {
            countryDatabase.countryDao().clearTable()
            return@withContext
        }
    }
}