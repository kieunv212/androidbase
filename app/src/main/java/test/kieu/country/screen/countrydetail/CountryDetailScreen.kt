package test.kieu.country.screen.countrydetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import test.kieu.country.component.ScaffoldComponent
import test.kieu.country.component.TopBarComponent
import test.kieu.country.model.Country
import test.kieu.country.ui.theme.CountryTheme


@Composable
fun CountryDetailScreen(
    navigateToBack: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Gray
    ) {
        CountryDetailScreenView()
    }
}

@Composable
private fun CountryDetailScreenView(viewModel: CountryDetailViewModel = hiltViewModel()){
    val country by viewModel.country.collectAsState()
    CountryTheme {
        ScaffoldComponent(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarComponent(
                    text = country.name.common.toString(),
                )
            },
            content = {
                ContentDetail(country)
            },
        )
    }
}

@Composable
private fun ContentDetail(country: Country) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(0.dp,100.dp,0.dp,0.dp)
            .clip(shape = RoundedCornerShape(15))
    ) {
        AsyncImage(
            model = country.flags.png,
            contentDescription = country.flags.alt,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )
    }
}