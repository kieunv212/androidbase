package test.kieu.country.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import test.kieu.country.db.CountryEntity

@Parcelize
data class Country(
    val flags: Flag,
    val name: Name
) : Parcelable {
    fun toCountryEntity(): CountryEntity {
        return CountryEntity(
            common = name.common.toString(),
            official = name.official.toString(),
            png = flags.png.toString(),
            svg = flags.svg.toString(),
            alt = flags.alt.toString()
        )
    }
}
