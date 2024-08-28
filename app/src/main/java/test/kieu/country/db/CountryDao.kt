package test.kieu.country.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countryEntity: CountryEntity)

    @Query("DELETE FROM Country")
    fun clearTable()

    @Query("SELECT * FROM Country")
    fun getAll(): List<CountryEntity>

    @Query("SELECT * FROM Country WHERE common=:common")
    fun search(common: String): List<CountryEntity>
}