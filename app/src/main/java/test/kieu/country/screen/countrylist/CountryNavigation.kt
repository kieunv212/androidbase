@file:OptIn(ExperimentalAnimationApi::class)

package test.kieu.country.screen.countrylist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import test.kieu.country.model.Country


const val countryNavigationRoute = "country_route"

fun NavController.navigateCharacter(
    navOptions: NavOptions? = null
) {
    this.navigate(countryNavigationRoute, navOptions)
}

fun NavGraphBuilder.countryScreen(navigateToDetail: (Country?) -> Unit) {
    composable(countryNavigationRoute) {
        CountryScreen(
            navigateToDetail = {
                navigateToDetail.invoke(it)
            }
        )
    }
}