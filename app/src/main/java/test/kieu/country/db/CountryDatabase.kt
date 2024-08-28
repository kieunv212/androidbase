package test.kieu.country.db

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Stable
@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        fun getDatabase(context: Context): CountryDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, CountryDatabase::class.java, tableName)
                .fallbackToDestructiveMigration()
                .build()
    }
}