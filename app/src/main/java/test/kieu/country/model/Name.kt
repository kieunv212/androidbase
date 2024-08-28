package test.kieu.country.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import test.kieu.country.db.CountryEntity

@Parcelize
data class Name(
    val common: String?,
    val official: String?
): Parcelable
