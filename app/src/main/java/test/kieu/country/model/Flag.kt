package test.kieu.country.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flag(
    val png: String?,
    val svg: String?,
    val alt: String?
) : Parcelable
