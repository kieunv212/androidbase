package test.kieu.country.repository

import test.kieu.country.model.Country
import test.kieu.country.network.CountryApiService
import javax.inject.Inject


class CountryRepository @Inject constructor(private val countryApiService: CountryApiService) {
    suspend fun getCountrys(): List<Country> {
        return countryApiService.getCountrys();
    }
}