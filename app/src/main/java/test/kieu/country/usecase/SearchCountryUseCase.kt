package test.kieu.country.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import test.kieu.country.db.CountryDatabase
import test.kieu.country.model.Country
import test.kieu.country.network.Resource
import java.io.IOException
import javax.inject.Inject

class SearchCountryUseCase @Inject constructor(
    private val countryDatabase: CountryDatabase
){
    suspend operator fun invoke(common: String): Flow<Resource<List<Country>?>> = flow {
        try {
            emit(Resource.Success(searchDataDB(common)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error!"))
        } catch (e: IOException) {
            // Load cache data if there is not internet connection
            emit(Resource.Success(searchDataDB("")))

        }
    }

    private suspend fun searchDataDB(common: String): List<Country> {
        return withContext(Dispatchers.IO) {
            val list = countryDatabase.countryDao().search(common)
            var listCountry = emptyList<Country>()
            if (list.isNotEmpty()) {
                listCountry = list.map { entity ->
                    entity.toCountry()
                }
            }

            return@withContext listCountry
        }
    }

}