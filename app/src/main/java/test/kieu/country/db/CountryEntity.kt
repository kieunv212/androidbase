package test.kieu.country.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import test.kieu.country.model.Country
import test.kieu.country.model.Flag
import test.kieu.country.model.Name

@Entity(tableName = tableName)
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "common") val common: String,
    @ColumnInfo(name = "official") val official: String,
    @ColumnInfo(name = "png") val png: String,
    @ColumnInfo(name = "svg") val svg: String,
    @ColumnInfo(name = "alt") val alt: String,
){
    fun toCountry(): Country {
        return Country(
            flags = Flag(png,svg,alt),
            name = Name(common, official)
        )
    }
}

const val tableName = "Country"

