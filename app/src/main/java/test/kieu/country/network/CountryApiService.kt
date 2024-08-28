package test.kieu.country.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.kieu.country.model.Country
import javax.inject.Inject

class CountryApiService @Inject constructor(private val countryApi: CountryApi){
    suspend fun getCountrys(): List<Country> {
        return withContext(Dispatchers.IO) {
            val countrys = countryApi.getCountrys()
            return@withContext countrys
        }
    }
}