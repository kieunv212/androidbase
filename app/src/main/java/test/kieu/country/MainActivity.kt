package test.kieu.country

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import test.kieu.country.screen.countrydetail.countryDetailScreen
import test.kieu.country.screen.countrydetail.navigateCountryDetail
import test.kieu.country.screen.countrylist.countryNavigationRoute
import test.kieu.country.screen.countrylist.countryScreen
import test.kieu.country.utils.Utility.toJson

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootNavHost()
        }

    }
}

@Composable
fun RootNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = countryNavigationRoute
    ) {
        countryScreen{it -> navController.navigateCountryDetail(it.toJson())}
        countryDetailScreen { navController.navigateUp() }
    }
}

