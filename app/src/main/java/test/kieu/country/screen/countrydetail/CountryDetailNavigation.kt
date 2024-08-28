@file:OptIn(ExperimentalAnimationApi::class)

package test.kieu.country.screen.countrydetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val countryDetailNavigationRoute = "country_detail_route"

fun NavController.navigateCountryDetail(
    countryDetail: String,
    navOptions: NavOptions? = null
) {
    this.navigate(countryDetailNavigationRoute.plus("?countryDetail=${countryDetail}"), navOptions)
}

fun NavGraphBuilder.countryDetailScreen(navigateToBack: () -> Unit) {
    composable(
        countryDetailNavigationRoute.plus("?countryDetail={countryDetail}"),
        content = {
            CountryDetailScreen(
                navigateToBack = navigateToBack
            )
        }
    )
}