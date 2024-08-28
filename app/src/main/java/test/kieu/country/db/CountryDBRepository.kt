package test.kieu.country.db

import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CountryDBRepository {
    suspend fun insert(countryEntity: CountryEntity)

    suspend fun search(common: String): List<CountryEntity>
}

class CountryDBImpl @Inject constructor(
    private val dao: CountryDao,
) : CountryDBRepository {
    override suspend fun insert(countryEntity: CountryEntity) {
        withContext(IO) {
            dao.insert(countryEntity)
        }
    }


    override suspend fun search(common: String): List<CountryEntity> {
        return withContext(IO) {
            var listCountry = dao.search(common)
            return@withContext listCountry
        }
    }

}