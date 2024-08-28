package test.kieu.country.network

import retrofit2.http.GET
import test.kieu.country.network.RetrofitConstants.Companion.ALL_COUNTRY
import test.kieu.country.model.Country

interface CountryApi {
    @GET(ALL_COUNTRY)
    suspend fun getCountrys(): List<Country>
}